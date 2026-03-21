(ns aoc-2025-06
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


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
  (map (fn [col] (apply (last col) (butlast col)))
       operations))


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
