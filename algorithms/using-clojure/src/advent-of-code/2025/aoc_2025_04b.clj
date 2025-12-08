(ns aoc-2025-04b
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

;; Problem
;;
;; Now, the Elves just need help accessing as much of the paper as they can.

;; Once a roll of paper can be accessed by a forklift, it can be removed. Once a roll of paper is removed, the forklifts might be able to access more rolls of paper, which they might also be able to remove. How many total rolls of paper could the Elves remove if they keep repeating this process?

;; Starting with the same example as above, here is one way you could remove as many rolls of paper as possible, using highlighted @ to indicate that a roll of paper is about to be removed, and using x to indicate that a roll of paper was just removed:

;; Initial state:
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

;; Remove 13 rolls of paper:
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

;; Remove 12 rolls of paper:
;; .......x..
;; .@@.x.x.@x
;; x@@@@...@@
;; x.@@@@..x.
;; .@.@@@@.x.
;; .x@@@@@@.x
;; .x.@.@.@@@
;; ..@@@.@@@@
;; .x@@@@@@@.
;; ....@@@...

;; Remove 7 rolls of paper:
;; ..........
;; .x@.....x.
;; .@@@@...xx
;; ..@@@@....
;; .x.@@@@...
;; ..@@@@@@..
;; ...@.@.@@x
;; ..@@@.@@@@
;; ..x@@@@@@.
;; ....@@@...

;; Remove 5 rolls of paper:
;; ..........
;; ..x.......
;; .x@@@.....
;; ..@@@@....
;; ...@@@@...
;; ..x@@@@@..
;; ...@.@.@@.
;; ..x@@.@@@x
;; ...@@@@@@.
;; ....@@@...

;; Remove 2 rolls of paper:
;; ..........
;; ..........
;; ..x@@.....
;; ..@@@@....
;; ...@@@@...
;; ...@@@@@..
;; ...@.@.@@.
;; ...@@.@@@.
;; ...@@@@@x.
;; ....@@@...

;; Remove 1 roll of paper:
;; ..........
;; ..........
;; ...@@.....
;; ..x@@@....
;; ...@@@@...
;; ...@@@@@..
;; ...@.@.@@.
;; ...@@.@@@.
;; ...@@@@@..
;; ....@@@...

;; Remove 1 roll of paper:
;; ..........
;; ..........
;; ...x@.....
;; ...@@@....
;; ...@@@@...
;; ...@@@@@..
;; ...@.@.@@.
;; ...@@.@@@.
;; ...@@@@@..
;; ....@@@...

;; Remove 1 roll of paper:
;; ..........
;; ..........
;; ....x.....
;; ...@@@....
;; ...@@@@...
;; ...@@@@@..
;; ...@.@.@@.
;; ...@@.@@@.
;; ...@@@@@..
;; ....@@@...

;; Remove 1 roll of paper:
;; ..........
;; ..........
;; ..........
;; ...x@@....
;; ...@@@@...
;; ...@@@@@..
;; ...@.@.@@.
;; ...@@.@@@.
;; ...@@@@@..
;; ....@@@...
;; Stop once no more rolls of paper are accessible by a forklift. In this example, a total of 43 rolls of paper can be removed.

;; Start with your original diagram. How many rolls of paper in total can be removed by the Elves and their forklifts?


(def ^:private DELTAS
  (for [r [-1 0 1]
        c [-1 0 1]
        :when (not (and (zero? r) (zero? c)))]
    [r c]))


(defn- accessible-rolls [lines]
  (let [rows (count lines)
        cols (count (first lines))
        deltas DELTAS]
    (for [rr (range rows)
          rc (range cols)
          :let [accessible? (->> deltas
                                 (map (fn [[r c]] (get-in lines [(+ rr r) (+ rc c)])))
                                 (filter #(= % \@))
                                 (#(< (count %) 4)))]
          :when (and
                 accessible?
                 (= (get-in lines [rr rc]) \@))]
        [rr rc])))


(defn- remove-accessible-rolls
  ([lines] (remove-accessible-rolls lines 0))
  ([lines removed]
  (let [accessible (accessible-rolls lines)]
    (if (empty? accessible)
      removed
      (recur
       (reduce (fn [acc r-c] (assoc-in acc r-c \.)) lines accessible)
       (+ removed (count accessible)))))))


(defn- parse-input [s]
  (->> s
       str/split-lines
       (map vec)
       vec))


(->> "aoc_2025_04.input"
     io/resource
     slurp
     parse-input
     remove-accessible-rolls
     println)
