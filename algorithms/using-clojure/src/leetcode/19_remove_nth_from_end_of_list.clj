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


(defn remove-nth-from-end [n lst]
  (let [;; Helper function returns a map with:
        ;; :index -> how far we are from the end (0-based)
        ;; :node  -> the reconstructed list tail
        helper (fn recur-fn [current-lst]
                 (if (empty? current-lst)
                   ;; Base case: End of list, index is 0
                   {:index 0 :node nil}

                   ;; Recursive step: go deeper first
                   (let [{:keys [index node]} (recur-fn (rest current-lst))
                         current-pos (inc index)]
                     (println index node)
                     ;; As we unwind, check if this is the node to skip
                     {:index current-pos
                      :node  (if (= current-pos n)
                               node ;; Skip this node (return the tail we got from below)
                               (cons (first current-lst) node))})))] ;; Keep this node
    (:node (helper lst))))


;; works ok for small lists...
(remove-nth-from-end 2 [1,2,3,4,5]) ;; [1,2,3,5]
(remove-nth-from-end 1 []) ;; []
(remove-nth-from-end 1 [1,2]) ;; [1]
;; ...but blows the stack for long lists
(remove-nth-from-end 2 (range 100000)) ;; :(


(defn remove-nth-from-end [n lst]
  ;; 1. Create the 'fast' pointer advanced by n
  (let [fast-head (drop n lst)]

    ;; Edge Case: If fast-head is empty, it means n >= length.
    ;; Usually this implies removing the head (start of list).
    (if (empty? fast-head)
      (rest lst)

      ;; 2. Use a helper to walk both pointers together
      ;; We use lazy-seq to avoid stack overflow
      ((fn helper [slow fast]
         (lazy-seq
           (if (empty? fast)
             ;; BASE CASE: Fast pointer hit the end.
             ;; We skip the current 'slow' node (the one to remove)
             ;; and return the rest of the list.
             (rest slow)

             ;; RECURSIVE STEP:
             ;; Keep the current 'slow' node, and advance both.
             (cons (first slow)
                   (helper (rest slow) (rest fast))))))
       lst fast-head))))


;; works ok for small lists...
(remove-nth-from-end 2 [1,2,3,4,5]) ;; [1,2,3,5]
(remove-nth-from-end 1 []) ;; []
(remove-nth-from-end 1 [1,2]) ;; [1]
;; ...and for long lists
(remove-nth-from-end 2 (range 100000)) ;; :)
