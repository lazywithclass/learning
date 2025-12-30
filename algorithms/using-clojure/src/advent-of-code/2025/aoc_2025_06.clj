(ns aoc-2025-06
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


;; Cephalopod math doesn't look that different from normal math. The math worksheet (your puzzle input) consists of a list of problems; each problem has a group of numbers that need to be either added (+) or multiplied (*) together.

;; However, the problems are arranged a little strangely; they seem to be presented next to each other in a very long horizontal list. For example:

;; 123 328  51 64
;;  45 64  387 23
;;   6 98  215 314
;; *   +   *   +
;; Each problem's numbers are arranged vertically; at the bottom of the problem is the symbol for the operation that needs to be performed. Problems are separated by a full column of only spaces. The left/right alignment of numbers within each problem can be ignored.

;; So, this worksheet contains four problems:

;; 123 * 45 * 6 = 33210
;; 328 + 64 + 98 = 490
;; 51 * 387 * 215 = 4243455
;; 64 + 23 + 314 = 401
;; To check their work, cephalopod students are given the grand total of adding together all of the answers to the individual problems. In this worksheet, the grand total is 33210 + 490 + 4243455 + 401 = 4277556.

;; Of course, the actual worksheet is much wider. You'll need to make sure to unroll it completely so that you can read the problems clearly.

;; Solve the problems on the math worksheet. What is the grand total found by adding together all of the answers to the individual problems?



(defn transpose-old
  ([matrix]
   ;; initialize acc with a vector of empty vectors, one for each column
   (transpose-old matrix (repeat (count (first matrix)) '())))
  ([lines acc]
   (cond
     (empty? lines) acc
     :else (recur (rest lines)
                  (mapv conj acc (first lines))))))


(defn solve [operations]
  (->> operations
       (map (fn [operation]
              ;; TODO remove reverse
              (apply (first (reverse operation)) (rest (reverse operation)))))))


;; even simpler and stunning solution
(defn transpose [matrix]
  (apply mapv vector matrix))


(defn- parse-input [inputs]
  (let [lines     (->> inputs
                       str/split-lines
                       (map (fn [line]
                              (-> line
                                  (str/replace #"\s+" " ")
                                  str/trim
                                  (str/split #"\s")))))
        num-lines (butlast lines)
        func-line (last lines)
        numbers   (map #(map Long/parseLong %) num-lines)
        functions (map #(-> % symbol resolve) func-line)]
    (concat numbers [functions])))


(->> "aoc_2025_06.input"
     io/resource
     slurp
     parse-input
     transpose
     solve
     (#(reduce + %))
     println
     )
