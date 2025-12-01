(ns aoc-2025-01
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.java.io :as io]))


(defn- move
  "a move L that exceeds 0 is a movement L that starts from 99
   a move R that exceeds 99 is a movement R that starts from 0"
  [dir pos amount]
  (match [dir pos amount]
         [_   _   0] pos
         [\L  0   a] (recur dir 99 (dec a))
         [\L  p   a] (recur dir (dec p) (dec a))
         [\R  99  a] (recur dir 0  (dec a))
         [\R  p   a] (recur dir (inc p) (dec a))))


(defn moves [start-pos instructions]
  (reduce (fn [[res pos] [dir amount]]
            (if (= (move dir pos amount) 0)
              [(inc res) (move dir pos amount)]
              [res (move dir pos amount)]))
          [0 start-pos]
          instructions))


(defn parse-input [s]
  (map (fn [line]
         (let [[_ dir amount] (re-matches #"(L|R)(\d+)" line)]
             [(first dir) (Integer/parseInt amount)]))
       (str/split-lines s)))


(->> "aoc_2025_01.input"
    io/resource
    slurp
    parse-input
    (moves 50)
    first)
