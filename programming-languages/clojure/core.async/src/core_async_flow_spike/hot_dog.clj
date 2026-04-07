(ns core-async-flow-spike.hot-dog
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))


(defn hot-dog-machine [in hot-dog-count]
  (let [out (chan)]
    (go (loop [hc hot-dog-count]
          (when-let [input (<! in)]
            (if (and (> hc 0) (= input 3))
              (do (>! out "hot dog")
                  (recur (dec hc)))
              (do (>! out "lettuce")
                  (recur hc)))))
        (close! out)) ;; only close channels you create
    out))


(let [in (chan)
      out (hot-dog-machine in 2)]

  (println "Started")
  
  (>!! in "pocket lint")
  (println (<!! out))

  (>!! in 3)
  (println (<!! out))

  (>!! in 3)
  (println (<!! out))

  (>!! in 3)
  (println (<!! out)) ;; should print nil

  (close! in) ;; only create channels you create

  (println "Finished"))
