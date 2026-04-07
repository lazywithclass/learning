(ns core-async-flow-spike.core
  (:require [clojure.core.async :as async :refer [go-loop <!]]
            [clojure.core.async.flow :as flow]
            [clojure.core.async.flow-monitor :as monitor]
            [clojure.data.xml :as xml]
            [clojure.string :as str]
            [clojure.pprint :as pp])
  (:gen-class))

;;; ---------------------------------------------------------------------------
;;; Telemetry — the only mutable state in the system
;;; ---------------------------------------------------------------------------

(def telemetry
  (atom {:polls      0
         :ids-found  0
         :downloaded 0   ; cumulative bytes
         :failed-ids []}))

;;; ---------------------------------------------------------------------------
;;; Logging
;;; ---------------------------------------------------------------------------

(defn- log [stage & parts]
  (println (str "[" (java.time.LocalTime/now) "] [" stage "] "
                (str/join " " (map str parts)))))

;;; ---------------------------------------------------------------------------
;;; Mock HTTP + XML helpers
;;; ---------------------------------------------------------------------------

(defn mock-fetch-xml
  "Returns an XML string with a random number of DocumentIDs (10–1 000).
  The wide range is intentional: it exercises the backpressure path when
  large batches overwhelm the 5-worker downloader pool."
  []
  (let [n    (+ 10 (rand-int 991))
        docs (str/join "\n"
               (map #(str "  <Document><DocumentID>DOC-"
                          (format "%05d" %) "</DocumentID></Document>")
                    (range n)))]
    (log "HTTP" "Fetched XML with" n "document IDs")
    (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
         "<Documents>\n" docs "\n</Documents>")))

(defn parse-document-ids
  "Walks the data.xml element tree and returns a vec of DocumentID strings."
  [xml-str]
  (vec
    (->> (xml/parse-str xml-str)
         :content                          ; <Document> elements
         (mapcat :content)                 ; their children
         (filter #(= :DocumentID (:tag %)))
         (mapcat :content))))              ; text nodes

;;; ---------------------------------------------------------------------------
;;; Step 1 — Ingestion
;;;
;;; Receives a ":trigger" pulse from the external timer, fetches + parses XML,
;;; then distributes the IDs evenly across five output ports (:id-0 … :id-4).
;;; The flow framework puts each ID vector onto the corresponding channel;
;;; if a downloader's channel is full it parks here — backpressure is free.
;;; ---------------------------------------------------------------------------

(def ingestion-step
  (flow/map->step
    {:describe
     (constantly
       {:ins      {:trigger "Tick signal from the external timer"}
        :outs     {:id-0 "Document IDs for downloader dl-0"
                   :id-1 "Document IDs for downloader dl-1"
                   :id-2 "Document IDs for downloader dl-2"
                   :id-3 "Document IDs for downloader dl-3"
                   :id-4 "Document IDs for downloader dl-4"}
        :workload :io})

     :init      (constantly {})
     :transition (fn [state _] state)

     :transform
     (fn [state _ _]
       (log "INGEST" "Poll triggered — fetching XML")
       (try
         (let [ids (parse-document-ids (mock-fetch-xml))
               n   (count ids)]
           (log "INGEST" "Distributing" n "IDs across 5 workers")
           (swap! telemetry #(-> % (update :polls inc) (update :ids-found + n)))
           ;; Round-robin: IDs 0,5,10… → :id-0 | 1,6,11… → :id-1 | etc.
           (let [groups (reduce (fn [acc [i id]]
                                  (update acc
                                          (keyword (str "id-" (mod i 5)))
                                          (fnil conj [])
                                          id))
                                {}
                                (map-indexed vector ids))]
             [state groups]))
         (catch Exception e
           (log "INGEST" "PARSE ERROR:" (.getMessage e))
           [state nil])))}))

;;; ---------------------------------------------------------------------------
;;; Step 2 — Downloader  (one process definition, instantiated ×5)
;;;
;;; Each process instance handles one document at a time. With five instances
;;; the pool sustains up to 5 concurrent downloads. Excess IDs park in each
;;; instance's :in channel buffer, bounding JVM memory use without any
;;; explicit coordination code.
;;; ---------------------------------------------------------------------------

(def downloader-step
  (flow/map->step
    {:describe
     (constantly
       {:ins      {:in "Document ID to download"}
        :outs     {:result "Download outcome map"}
        :workload :io})   ; runs on the IO thread pool — Thread/sleep is fine

     :init      (constantly {})
     :transition (fn [state _] state)

     :transform
     (fn [state _ id]
       ;; Simulate real-world network latency (200 ms – 1 000 ms).
       (Thread/sleep (+ 200 (rand-int 800)))
       (if (< (rand) 0.10)
         ;; 10 % simulated failure — reported to collector, does NOT crash
         (do (log "DOWNLOAD" "FAIL id=" id)
             [state {:result [{:id id :status :error :reason "simulated network error"}]}])
         (let [bytes (+ 1024 (rand-int 49152))]
           (log "DOWNLOAD" "OK   id=" id "bytes=" bytes)
           [state {:result [{:id id :status :ok :bytes bytes}]}])))}))

;;; ---------------------------------------------------------------------------
;;; Step 3 — Collector
;;;
;;; Merges results from all five downloader outputs and updates telemetry.
;;; Failed IDs are recorded in the atom; the pipeline continues regardless.
;;; ---------------------------------------------------------------------------

(def collector-step
  (flow/map->step
    {:describe
     (constantly
       {:ins      {:in-0 "Results from dl-0"
                   :in-1 "Results from dl-1"
                   :in-2 "Results from dl-2"
                   :in-3 "Results from dl-3"
                   :in-4 "Results from dl-4"}
        :outs     {}
        :workload :mixed})

     :init      (constantly {})
     :transition (fn [state _] state)

     :transform
     (fn [state _ result]
       (case (:status result)
         :ok    (do (swap! telemetry update :downloaded + (:bytes result))
                    [state nil])
         :error (do (swap! telemetry update :failed-ids conj (:id result))
                    (log "COLLECTOR" "Recorded failure for" (:id result))
                    [state nil])
         (do (log "COLLECTOR" "Unknown result shape:" result)
             [state nil])))}))

;;; ---------------------------------------------------------------------------
;;; Flow graph
;;;
;;;   [external timer]
;;;        │ inject([:ingestion :trigger])
;;;        ▼
;;;   ingestion  ──:id-0──▶  dl-0 ──:result──▶┐
;;;              ──:id-1──▶  dl-1 ──:result──▶│
;;;              ──:id-2──▶  dl-2 ──:result──▶├──▶ collector
;;;              ──:id-3──▶  dl-3 ──:result──▶│
;;;              ──:id-4──▶  dl-4 ──:result──▶┘
;;;
;;; Backpressure chain (right → left):
;;;   collector full? → dl-N :result ch full → dl-N parks in transform
;;;   → dl-N :in ch fills → ingestion :id-N ch parks in transform
;;;   → ingestion can't consume :trigger → timer's @(inject ...) blocks
;;; ---------------------------------------------------------------------------

(defn build-flow []
  (flow/create-flow
    {:procs
     {:ingestion {:proc (flow/process ingestion-step)}
      :dl-0      {:proc (flow/process downloader-step)}
      :dl-1      {:proc (flow/process downloader-step)}
      :dl-2      {:proc (flow/process downloader-step)}
      :dl-3      {:proc (flow/process downloader-step)}
      :dl-4      {:proc (flow/process downloader-step)}
      :collector {:proc (flow/process collector-step)}}

     :conns
     [[[:ingestion :id-0] [:dl-0 :in]]
      [[:ingestion :id-1] [:dl-1 :in]]
      [[:ingestion :id-2] [:dl-2 :in]]
      [[:ingestion :id-3] [:dl-3 :in]]
      [[:ingestion :id-4] [:dl-4 :in]]
      [[:dl-0 :result]    [:collector :in-0]]
      [[:dl-1 :result]    [:collector :in-1]]
      [[:dl-2 :result]    [:collector :in-2]]
      [[:dl-3 :result]    [:collector :in-3]]
      [[:dl-4 :result]    [:collector :in-4]]]}))

;;; ---------------------------------------------------------------------------
;;; System lifecycle
;;; ---------------------------------------------------------------------------

(defn start!
  "Starts the flow and a background timer thread.

  The timer calls flow/inject every interval-ms milliseconds. Because inject
  returns a future that completes only once the channel accepts the message,
  blocking @(inject ...) on the timer thread IS the backpressure mechanism:
  if ingestion is still processing a large batch, the timer waits rather than
  piling up ticks."
  [g interval-ms]
  (let [{:keys [error-chan]} (flow/start g)
        srv                  (monitor/start-server {:flow g :port 9998})]
    (flow/resume g)

    ;; Log every error reported by any process without crashing the pipeline.
    (go-loop []
      (when-let [err (<! error-chan)]
        (log "ERROR" err)
        (recur)))

    (let [timer (future
                  (loop []
                    (Thread/sleep interval-ms)
                    (log "TICKER" "Firing tick → ingestion")
                    @(flow/inject g [:ingestion :trigger] [:tick])
                    (recur)))]
      {:flow g :timer timer :monitor srv})))

(defn stop!
  "Cancels the timer and stops the flow cleanly."
  [{:keys [flow timer monitor]}]
  (log "SYSTEM" "Shutting down…")
  (future-cancel timer)
  (monitor/stop-server monitor)
  (flow/stop flow)
  (log "SYSTEM" "Shutdown complete"))

;;; ---------------------------------------------------------------------------
;;; Entry point
;;; ---------------------------------------------------------------------------

(defn -main
  "Starts the pipeline. Optional first arg: poll interval in minutes (default 1).
  Press Enter to trigger a graceful shutdown and print final telemetry."
  [& args]
  (reset! telemetry {:polls 0 :ids-found 0 :downloaded 0 :failed-ids []})
  (let [interval-min (or (some-> args first (Long/parseLong)) 1)
        interval-ms  (* interval-min 60 1000)
        g            (build-flow)
        sys          (start! g interval-ms)]
    (println "\nPipeline running. Poll interval:" interval-min "minute(s).")
    (println "Monitor: http://localhost:9998/index.html#/?port=9998")
    (println "Press Enter to stop.\n")
    (read-line)
    (stop! sys)
    (println "\nFinal telemetry:")
    (pp/pprint @telemetry)))
