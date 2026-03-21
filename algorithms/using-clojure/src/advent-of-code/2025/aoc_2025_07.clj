(ns aoc-2025-07
  (:import [clojure.lang IPersistentSortedSet])
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]
            [clojure.lang.IPersistentSortedSet]
            [clojure.java.io :as io]
            [criterium.core :refer [bench quick-bench]]))


;; (apply-beams [7] [7])
(defn apply-beams [beam-idxs splitter-idxs]
  (loop [beams beam-idxs
         splitters splitter-idxs
         ^IPersistentSortedSet next-beams (sorted-set)
         count 0]
    (let [beam (first beams)
          splitter (first splitters)]
      (cond
        (nil? beam)
        [(vec next-beams) count]

        (= beam splitter)
        (recur (rest beams)
               (rest splitters)
               (conj next-beams (dec splitter) (inc splitter))
               (inc count))

        (< splitter beam)
        (recur beams (rest splitters) next-beams count)

        :else
        ;; beam passes through without splitting
        (recur (rest beams) splitters (conj next-beams (first beams)) count)))))


;; (solve [7] [[7]])
(defn solve
  [beam-idxs splitter-layers]
   (loop [current-beams beam-idxs
          remaining-layers splitter-layers
          total-splits 0]
     (let [current-splitters (first remaining-layers)]
       (cond
         (empty? current-splitters) total-splits
         :else (let [[next-beams split-count] (apply-beams current-beams current-splitters)]
                 (recur next-beams
                        (rest remaining-layers)
                        (+ total-splits split-count)))))))


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
     (solve [70]))
