(ns aoc-2025-05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(defn solve [[ranges ids]]
  (->> ids
       (filter (fn [id]
                 (some (fn [[from to]] (and (>= id from) (<= id to))) ranges)))
       count))


(defn- parse-input [inputs]
  (->> inputs
       (#(map str/split-lines %))
       ((fn [[ranges ids]]
          [(->> ranges
                (map #(str/split % #"-"))
                (map #((fn [[a b]] [(Long/parseLong a) (Long/parseLong b)]) %)))
           (map Long/parseLong ids)
           ]))
       vec))


(->> "aoc_2025_05.input"
     io/resource
     slurp
     (#(str/split % #"\n\n"))
     parse-input
     (#(solve %))
     )
