(ns leetcode.19-remove-nth-from-end-of-list)


;; Given the head of a linked list, remove the nth node from the end of the list and return its head.


(defn remove-nth-from-end [n coll]
  (let [len (count coll)
        split-idx (- len n)]
    (if (or (< split-idx 0) (> n len))
      coll
      (concat (take split-idx coll)
              (drop (inc split-idx) coll)))))


(remove-nth-from-end 2 [1,2,3,4,5]) ;; [1,2,3,5]

(remove-nth-from-end 1 []) ;; []

(remove-nth-from-end 1 [1,2]) ;; [1]
