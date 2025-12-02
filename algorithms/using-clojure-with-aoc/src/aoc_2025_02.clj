(ns aoc-2025-02
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.java.io :as io]))


(defn- is-invalid?
  "an id is invalid if it is composed only of repetitions of the same sequence"
  ([id] (is-invalid? id 1))
  ([id chunk-len]
   (cond
     (> (* 2 chunk-len) (count id)) false
     :else (let [chunk (take chunk-len id)
                 check (fn [id]
                         (cond
                           (empty? id) true
                           :else (if (= chunk (take chunk-len id))
                                   (recur (drop chunk-len id))
                                   false)))]
             (if (check (drop chunk-len id))
               true
               (recur id (inc chunk-len)))))))


(defn- unroll-range
  "given a start and end, return a sequence of all numbers between them inclusive"
  [[start end]]
  (range start (inc end)))


(defn parse-input [s]
  (->> (re-seq #"(\d+)-(\d+)" s)
       (map (fn [[_ a b]] ;; _ is to ignore the full match
              [(Long/parseLong a) (Long/parseLong b)]))))


(->> "aoc_2025_02.input"
     io/resource
     slurp
     parse-input
     (mapcat unroll-range)
     (map (fn [n] (->> (str n) seq)))
     (filter is-invalid?)
     (map (fn [seq] (Long/parseLong (apply str seq))))
     (reduce +)
     println
     )
