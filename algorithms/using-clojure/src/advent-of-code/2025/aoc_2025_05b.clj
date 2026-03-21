(ns aoc-2025-05b
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


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
