(ns aoc-2025-04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(defn- calculate-deltas []
  (for [r [-1 0 1]
        c [-1 0 1]
        :when (not (and (zero? r) (zero? c)))]
    [r c]))


(defn neighbours [lines]
  (let [rows (count lines)
        cols (count (first lines))
        deltas (calculate-deltas)]
    (for [rr (range rows)
          rc (range cols)
          :when (= (get-in lines [rr rc]) \@)]
      (->> deltas
           (map (fn [[r c]] (get-in lines [(+ rr r) (+ rc c)])))
           (filter #(= % \@))
           count))))


(defn- parse-input [s]
  (->> s
       str/split-lines
       (map vec)
       vec))


(->> "aoc_2025_04.input"
     io/resource
     slurp
     parse-input
     neighbours
     (filter #(< % 4))
     count
     println)
