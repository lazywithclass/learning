(ns leetcode.2-add-two-numbers
  (:require [clojure.core.match :refer [match]]))

;; You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

;; You may assume the two numbers do not contain any leading zero, except the number 0 itself.

;; example
;; 2 -> 4 -> 3
;; 5 -> 6 -> 4
;; result:
;; 7 -> 0 -> 8


;; in clojure a list is a singly linked list
;; the following solution might be "composable" but the last
;; one is way better and idiomatic
(defn as-number [numbers]
  (loop [ns   numbers
         mult 1
         res  0]
    (let [n (first ns)]
      (if (nil? n)
        res
        (recur (rest ns) (* mult 10) (+ res (* n mult)))))))

(defn as-list [n]
  (->> n
       (iterate #(quot % 10))
       (take-while pos?)
       (map #(rem % 10))))

(defn sum-lists [l1 l2]
  (as-list (+ (as-number l1) (as-number l2))))


;; solution with loop recur
(defn sum-lists [l1 l2]
  (loop [l1    l1
         l2    l2
         carry 0
         tot   '()]
    (if (and (empty? l1) (empty? l2) (zero? carry))
      (reverse tot)
      (let [n1        (first l1)
            n2        (first l2)
            v1        (or n1 0)
            v2        (or n2 0)
            sum       (+ v1 v2 carry)
            new-digit (rem sum 10)
            new-carry (quot sum 10)]
        (recur (rest l1)
               (rest l2)
               new-carry
               (cons new-digit tot))))))


;; this is a better approach
(defn sum-lists [l1 l2]
  (letfn [(sum-step [l1 l2 carry]
            (lazy-seq
              (if (and (empty? l1) (empty? l2) (zero? carry))
                nil
                (let [n1  (or (first l1) 0)
                      n2  (or (first l2) 0)
                      tot (+ n1 n2 carry)]
                  (cons (rem tot 10)
                        (sum-step (rest l1) (rest l2) (quot tot 10)))))))]
    (sum-step l1 l2 0)))

(sum-lists '(1 2) '(3 4)) ;; (4 6)

(sum-lists '(2 4 3) '(5 6 4)) ;; (7 0 8)

(sum-lists '(2 4 3 5) '(5 6 4)) ;; (7 0 8 5)

(sum-lists '(2 4 3) '(5 6 9 1 1)) ;; (7 0 3 2 1)
