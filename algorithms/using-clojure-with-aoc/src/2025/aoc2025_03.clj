(ns aoc2025-03
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


;; the idea is to use a stack to build the largest number
;; if we find a better digit candidate and we still have enough digits left
;; to reach the target length, we pop the smaller digits from the stack
(defn solve [digits]
  (let [target-len 12]
    (loop [remaining-digits digits
           stack []
           digits-left (count digits)]
      (if (empty? remaining-digits)
        (Long/parseLong (str/join stack))
        (let [current (first remaining-digits)
              new-stack (loop [s stack]
                          (if (and (seq s)
                                   (> current (peek s))
                                   (>= (dec (+ (count s) digits-left)) target-len))
                            (recur (pop s))
                            s))]
          (recur (rest remaining-digits)
                 (if (< (count new-stack) target-len)
                   (conj new-stack current)
                   new-stack)
                 (dec digits-left)))))))


(defn parse-input [s]
  (->> s
       str/split-lines
       (map (fn [line]
              (->> line
                   seq
                   (map (fn [c] (Long/parseLong (str c))))
                   vec)))))


(->> "aoc_2025_03.input"
     io/resource
     slurp
     parse-input
     (map solve)
     (reduce +)
     println)
