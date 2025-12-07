(ns aoc-2025-01b
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.java.io :as io]))


(defn- register-move [[dir steps]]
  (match dir
         \L (repeat steps -1)
         \R (repeat steps 1)))


(defn play-moves [from-pos moves]
  (reductions (fn [pos move] (mod (+ pos move) 100))
              from-pos
              moves))


(defn parse-input [s]
  (map (fn [line]
         (let [[_ dir amount] (re-matches #"(L|R)(\d+)" line)]
           [(first dir) (Integer/parseInt amount)]))
       (str/split-lines s)))


(->> "aoc_2025_01.input"
     io/resource
     slurp
     parse-input
     (mapcat register-move)
     (play-moves 50)
     (filter zero?)
     count)
