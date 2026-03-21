(ns aoc-2025-07b
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


;; beam-map: sorted-map of {position -> timeline-count}
;; When a beam hits a splitter, it splits into left and right,
;; each inheriting the timeline count. Beams at the same position
;; have their counts summed.
(defn apply-beams [beam-map splitter-idxs]
  (let [splitter-set (set splitter-idxs)]
    (reduce (fn [acc [pos cnt]]
              (if (contains? splitter-set pos)
                (-> acc
                    (update (dec pos) (fnil + 0) cnt)
                    (update (inc pos) (fnil + 0) cnt))
                (update acc pos (fnil + 0) cnt)))
            (sorted-map)
            beam-map)))


;; (solve 7 [[7]])
;; Returns the total number of timelines after all layers.
(defn solve
  [start-pos splitter-layers]
  (->> (reduce apply-beams (sorted-map start-pos 1) splitter-layers)
       vals
       (reduce +)))


(defn parse-input [input]
  (->> input
       str/split-lines
       (map (fn [line]
               (keep-indexed #(when (= %2 \^) %1) line)))
       (filter seq)))


(->> "aoc_2025_07.input"
     io/resource
     slurp
     parse-input
     (solve 70)
     println)
