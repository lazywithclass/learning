(ns aoc-2025-05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


;; The database operates on ingredient IDs. It consists of a list of fresh ingredient ID ranges, a blank line, and a list of available ingredient IDs. For example:

;; 3-5
;; 10-14
;; 16-20
;; 12-18

;; 1
;; 5
;; 8
;; 11
;; 17
;; 32
;; The fresh ID ranges are inclusive: the range 3-5 means that ingredient IDs 3, 4, and 5 are all fresh. The ranges can also overlap; an ingredient ID is fresh if it is in any range.

;; The Elves are trying to determine which of the available ingredient IDs are fresh. In this example, this is done as follows:

;; Ingredient ID 1 is spoiled because it does not fall into any range.
;; Ingredient ID 5 is fresh because it falls into range 3-5.
;; Ingredient ID 8 is spoiled.
;; Ingredient ID 11 is fresh because it falls into range 10-14.
;; Ingredient ID 17 is fresh because it falls into range 16-20 as well as range 12-18.
;; Ingredient ID 32 is spoiled.
;; So, in this example, 3 of the available ingredient IDs are fresh.

;; Process the database file from the new inventory management system. How many of the available ingredient IDs are fresh?


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
