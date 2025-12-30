(ns aoc-2025-06b
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [criterium.core :refer [bench quick-bench]]))


;; Cephalopod math is written right-to-left in columns. Each number is given in its own column, with the most significant digit at the top and the least significant digit at the bottom. (Problems are still separated with a column consisting only of spaces, and the symbol at the bottom of the problem is still the operator to use.)

;; Here's the example worksheet again:

;; 123 328  51 64
;;  45 64  387 23
;;   6 98  215 314
;; *   +   *   +
;; Reading the problems right-to-left one column at a time, the problems are now quite different:

;; The rightmost problem is 4 + 431 + 623 = 1058
;; The second problem from the right is 175 * 581 * 32 = 3253600
;; The third problem from the right is 8 + 248 + 369 = 625
;; Finally, the leftmost problem is 356 * 24 * 1 = 8544
;; Now, the grand total is 1058 + 3253600 + 625 + 8544 = 3263827.


(def ops {\* *' \+ +'})


(defn transpose-padded [matrix]
  (let [max-len (apply max (map count matrix))]
    (apply mapv vector
           (map #(take max-len (concat % (repeat \space)))
                matrix))))


(defn parse-input [inputs]
  (-> inputs
      (str/split #"\n")
      transpose-padded))


;; This part have been subject to a few tests to see the fastest implementation
;; which ended up being

(defn chars->num ^long [line-chars]
  (reduce (fn [^long acc c]
            (if (Character/isDigit c)
              (+ (* acc 10) (Character/digit c 10))
              acc))
          0
          line-chars))

(defn chars->num-fastah ^long [line-chars]
  (loop [s   (seq line-chars)
         acc (long 0)]
    (if s
      (let [c (first s)
            val (Character/digit ^char c 10)]
        (recur (next s)
               (if (neg? val)
                 acc
                 (+ (* acc 10) val))))
      acc)))

(defn str->num [line]
  (->> line
       (apply str)
       str/trim
       Long/parseLong))

;; tests
(def valid-input [\1 \2 \3 \4 \5 \6 \7 \8 \9 \0 \1 \2 \3 \4 \5 \6 \7 \8])

(quick-bench (dotimes [_ 10000] (chars->num valid-input))) ;; 2.36 sec

(quick-bench (dotimes [_ 10000] (chars->num-fastah valid-input))) ;; 7.84 ms

(quick-bench (dotimes [_ 10000] (str->num valid-input))) ;; 8.04 ms
;; tests end


(defn solve [matrix]
  (loop [m matrix
         op (ops (last (first m)))
         local-tot (if (= op *') 1 0)
         tot 0]
    (let [last-char (last (first m))
          column-with-numbers (some #(not= % \space) (first m))]
      (if (empty? m)
        (+ tot local-tot)
        (recur (rest m)
               (if (not= last-char \space)
                 (ops last-char)
                 op)
               (if column-with-numbers
                 (op local-tot (chars->num-fastah (butlast (first m))))
                 (if (= op *') 1 0))
               (if column-with-numbers
                 tot
                 (+ tot local-tot)))))))


(->> "aoc_2025_06.input"
     io/resource
     slurp
     parse-input
     solve
     println)
