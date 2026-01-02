(ns leetcode.1-two-sum
  (:import [clojure.lang PersistentVector]))


;; Assignment

;; Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
;; You may assume that each input would have exactly one solution, and you may not use the same element twice.
;; You can return the answer in any order.


;; it's guaranteed that the two numbers are there
(defn find-tot [^PersistentVector numbers target]
  (loop [from 0
         to   1]
    (if (>= to (count numbers))
      (recur (inc from) (+ from 2))
      (let [sum (+ (nth numbers from)
                   (nth numbers to))]
        (cond
          (= sum target) [from to]
          :else          (recur from (inc to)))))))

(find-tot [1 2 3 4 5 6] 11) ;; [4 5]

(find-tot [1 2 3 4 5 6] 3) ;; [0 1]

;; doesnt need to be sorted
(find-tot [2 6 5 4 1 3] 7) ;; [0 2]
