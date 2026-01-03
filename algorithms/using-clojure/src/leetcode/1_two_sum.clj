(ns leetcode.1-two-sum
  (:import [clojure.lang PersistentVector]))


;; Assignment

;; Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
;; You may assume that each input would have exactly one solution, and you may not use the same element twice.
;; You can return the answer in any order.


;; this is slow, I'm not sure it's n^2, but slow
;; anyway, discarded solution, not to be reviewed
(defn find-tot-slow [^PersistentVector numbers target]
  (loop [from 0
         to   1]
    (if (>= to (count numbers))
      (recur (inc from) (+ from 2))
      (let [sum (+ (nth numbers from)
                   (nth numbers to))]
        (cond
          (= sum target) [from to]
          :else          (recur from (inc to)))))))


;; trade space for speed
;; put all numbers into a map (the value is the index) O(n)
;; go through the keys of the map
;;   subtract the current value from target
;;   get that value as key from the map
;;   is it there?
;;     if so finished
;;     if not continue
;;
;; this solution does one pass too much to build the map
;; the solution is to build the map while iterating and considering it
;; as the set of non working solutions
(defn find-tot-not-there-yet [^PersistentVector numbers target]
  (let [m (reduce-kv
           (fn [m idx n] (assoc m n idx))
           {}
           numbers)]
    (loop [nums (keys m)]
      (let [num    (first nums)
            wanted (- target num)]
        (if (m wanted)
          [(m num) (m wanted)]
          (recur (rest nums)))))))


;; processed stores {value index} for already processed numbers
;; the idea is that if the map hasnt seen the missing number yet
;; we need to continue recurring
(defn find-tot [^PersistentVector numbers target]
  (loop [i 0
         processed {}]
      (let [num              (nth numbers i)
            complement       (- target num)
            complement-index (get processed complement)]
        (if complement-index
          [complement-index i]
          (recur (inc i) (assoc processed num i))))))


(reduce-kv (fn [m k v] (assoc m k v)) {} [1 2 3 4 5])


(find-tot [1 2 3 4 5 6] 11) ;; [4 5]

(find-tot [1 2 3 4 5 6] 3) ;; [0 1]

;; doesnt need to be sorted
(find-tot [2 6 5 4 1 3] 7) ;; [0 2]
