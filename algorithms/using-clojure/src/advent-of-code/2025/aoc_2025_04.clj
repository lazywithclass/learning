(ns aoc-2025-04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

;; Problem
;;
;; The rolls of paper (@) are arranged on a large grid; the Elves even have a helpful diagram (your puzzle input) indicating where everything is located.
;;
;; For example:
;;
;; ..@@.@@@@.
;; @@@.@.@.@@
;; @@@@@.@.@@
;; @.@@@@..@.
;; @@.@@@@.@@
;; .@@@@@@@.@
;; .@.@.@.@@@
;; @.@@@.@@@@
;; .@@@@@@@@.
;; @.@.@@@.@.
;; The forklifts can only access a roll of paper if there are fewer than four rolls of paper in the eight adjacent positions. If you can figure out which rolls of paper the forklifts can access, they'll spend less time looking and more time breaking down the wall to the cafeteria.
;;
;; In this example, there are 13 rolls of paper that can be accessed by a forklift (marked with x):
;;
;; ..xx.xx@x.
;; x@@.@.@.@@
;; @@@@@.x.@@
;; @.@@@@..@.
;; x@.@@@@.@x
;; .@@@@@@@.@
;; .@.@.@.@@@
;; x.@@@.@@@@
;; .@@@@@@@@.
;; x.x.@@@.x.
;; Consider your complete diagram of the paper roll locations. How many rolls of paper can be accessed by a forklift?


(defn neighbours [lines]
  (let [rows (count lines)
        cols (count (first lines))
        deltas [[-1 -1] [-1 0] [-1 1]
                [ 0 -1]        [0  1]
                [ 1 -1] [ 1 0] [1  1]]]
    (for [rr (range rows)
          rc (range cols)]
      (reduce (fn [acc neighbour-cell] (cond
                                         (and
                                            (= (get-in lines [rr rc]) \@)
                                            (= neighbour-cell \@))
                                         (inc acc)

                                         (= (get-in lines [rr rc]) \.)
                                         10

                                         :else acc))
              0
              (map (fn [[r c]]
                     (get-in lines [(+ rr r) (+ rc c)])) deltas)))))


(defn- parse-input [s]
  (->> s
       str/split-lines
       (map vec)
       vec))


(->> "aoc_2025_04.input"
     io/resource
     slurp
     parse-input
     neighbours
     (filter #(< % 4))
     count
     println)
