(ns aoc-2025-04b
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(def ^:private DELTAS
  (for [r [-1 0 1]
        c [-1 0 1]
        :when (not (and (zero? r) (zero? c)))]
    [r c]))


(defn- accessible-rolls [lines]
  (let [rows (count lines)
        cols (count (first lines))
        deltas DELTAS]
    (for [rr (range rows)
          rc (range cols)
          :let [accessible? (->> deltas
                                 (map (fn [[r c]] (get-in lines [(+ rr r) (+ rc c)])))
                                 (filter #(= % \@))
                                 (#(< (count %) 4)))]
          :when (and
                 accessible?
                 (= (get-in lines [rr rc]) \@))]
        [rr rc])))


(defn- remove-accessible-rolls
  ([lines] (remove-accessible-rolls lines 0))
  ([lines removed]
  (let [accessible (accessible-rolls lines)]
    (if (empty? accessible)
      removed
      (recur
       (reduce (fn [acc r-c] (assoc-in acc r-c \.)) lines accessible)
       (+ removed (count accessible)))))))


(defn- parse-input [s]
  (->> s
       str/split-lines
       (map vec)
       vec))


(->> "aoc_2025_04.input"
     io/resource
     slurp
     parse-input
     remove-accessible-rolls
     println)
