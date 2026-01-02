(ns leetcode.35-search-insert-position)


;; Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

;; You must write an algorithm with O(log n) runtime complexity.


(defn find-position [arr n]
  (loop [lo 0
         hi (dec (count arr))]
    (let [piv (quot (+ lo hi) 2)
          cur (nth arr piv)]
    (cond
      (> lo hi) lo
      (= cur n) piv
      (< cur n) (recur (inc piv) hi)
      (> cur n) (recur lo (dec piv))))))

(find-position [1 2 3 4 5 6] 5) ;; 4

(find-position [1 3 4 5 6] 2) ;; 1

(find-position [1 2 3 4 6] 5) ;; 4

(find-position [1 2 3 4 5] 42) ;; 5

(find-position [1 2 3 4 5] 0) ;; 0
