(ns leetcode.35-search-insert-position
  (:import [clojure.lang PersistentVector]))


;; Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

;; You must write an algorithm with O(log n) runtime complexity.


(defn search-insert [^PersistentVector arr ^long tgt]
  (loop [lo 0
         hi (dec (count arr))]
    (let [piv (quot (+ lo hi) 2)
          cur (nth arr piv)]
      (cond
        (> lo hi)   lo
        (= cur tgt) piv
        (< cur tgt) (recur (inc piv) hi)
        (> cur tgt) (recur lo (dec piv))))))

(search-insert [1 2 3 4 5 6] 5) ;; 4

(search-insert [1 3 4 5 6] 2) ;; 1

(search-insert [1 2 3 4 6] 5) ;; 4

(search-insert [1 2 3 4 5] 42) ;; 5

(search-insert [1 2 3 4 5] 0) ;; 0
