(ns aoc-2025-05b
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


;; So that they can stop bugging you when they get new inventory, the Elves would like to know all of the IDs that the fresh ingredient ID ranges consider to be fresh. An ingredient ID is still considered fresh if it is in any range.

;; Now, the second section of the database (the available ingredient IDs) is irrelevant. Here are the fresh ingredient ID ranges from the above example:

;; 3-5
;; 10-14
;; 16-20
;; 12-18
;; The ingredient IDs that these ranges consider to be fresh are 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, and 20. So, in this example, the fresh ingredient ID ranges consider a total of 14 ingredient IDs to be fresh.

;; Process the database file again. How many ingredient IDs are considered to be fresh according to the fresh ingredient ID ranges?


(defn squash-ranges [ranges]
  (->> (rest ranges)
       (reduce (fn [acc [from to]]
                 (let [tail (last acc)]
                   (cond
                     (and (>= from (first tail)) (<= to (second tail)))
                     acc

                     (and (>= from (first tail)) (<= from (second tail)) (>= to (second tail)))
                     (conj (vec (butlast acc)) [(first tail) to])

                     :else
                     (conj acc [from to]))))
               [(first ranges)])))


(defn- parse-input [inputs]
  (->> inputs
       str/split-lines
       ((fn [ranges]
          (->> ranges
               (map #(str/split % #"-"))
               (map #((fn [[a b]] [(Long/parseLong a) (Long/parseLong b)]) %)))))
       vec))


(->> "aoc_2025_05.input"
     io/resource
     slurp
     parse-input
     (sort-by first)
     (#(squash-ranges %))
     (reduce (fn [acc [from to]]
               (-> acc
                   (+ (- to from))
                   inc)) ;; 3 5 -> 2 but should count the boundaries too
             0)
     println)
