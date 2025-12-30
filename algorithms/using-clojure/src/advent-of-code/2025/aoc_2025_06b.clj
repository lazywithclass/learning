(ns aoc-2025-06b
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]))


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


(defn str->num [line]
  (->> line
      (apply str)
      str/trim
      Long/parseLong))


(defn solve [matrix]
  (loop [m matrix
         op (ops (last (first m)))
         local-tot (if (= op *') 1 0)
         tot 0]
    (if (empty? m)
      (+ tot local-tot)
      (recur (rest m)
             (if (not= (last (first m)) \space)
               (ops (last (first m)))
               op)
             (if (some #(not= % \space) (first m))
               (op local-tot (str->num (butlast (first m)))) ;; at least a number
               (if (= op *') 1 0))
             (if (some #(not= % \space) (first m))
               tot ;; at least a number
               (+ tot local-tot))))))


(->> "aoc_2025_06.input"
     io/resource
     slurp
     parse-input
     solve
     println)
