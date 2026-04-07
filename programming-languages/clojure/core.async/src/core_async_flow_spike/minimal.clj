(ns core-async-flow-spike.minimal
  (:require [clojure.core.async :refer [<!! timeout]]
            [clojure.core.async.flow :as flow])
  (:gen-class))

;; Two processes connected by a channel:
;;
;;   inject ─> [doubler] ─> channel ─> [printer]

(def doubler
  ;; lift1->step wraps a plain fn into a step with one :in and one :out port.
  (flow/lift1->step (fn [n] (* 2 n))))

(def printer
  (flow/map->step
    {:describe   (constantly {:ins {:in "value to print"} :outs {}})
     :init       (constantly nil)
     :transition (fn [s _] s)
     :transform  (fn [_ _ v] (println "→" v) [nil nil])}))

(def g
  (flow/create-flow
    {:procs {:doubler {:proc (flow/process doubler)}
             :printer {:proc (flow/process printer)}}
     :conns [[[:doubler :out] [:printer :in]]]}))

(defn -main [& _]
  (flow/start g)
  (flow/resume g)
  (doseq [n (range 1 6)]
    @(flow/inject g [:doubler :in] [n]))  ; blocks until channel accepts
  (<!! (timeout 500))                     ; let results drain
  (flow/stop g))
