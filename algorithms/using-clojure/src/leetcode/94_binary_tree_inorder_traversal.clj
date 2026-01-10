(ns leetcode.94-binary-tree-inorder-traversal)


;; Given the root of a binary tree, return the inorder traversal of its nodes' values.


(defrecord Node [value left right])


(defn in-order [node]
  (when node
    (concat (in-order (:left node))
            [(:value node)]
            (in-order (:right node)))))

;; concat ignores nils
(concat [1 2 3] nil [4] nil [5])

(def record-tree
  (->Node 1
          (->Node 2
                  (->Node 4 nil nil)
                  (->Node 5
                          (->Node 6 nil nil)
                          (->Node 7 nil nil)))
          (->Node 3
                  nil
                  (->Node 8
                          (->Node 9 nil nil)
                          nil))))

(in-order record-tree) ;; [4,2,6,5,7,1,3,9,8]


;; approach with tail call and stack accumulation
(defn in-order-tail [root]
  (loop [node  root
         stack '()
         acc   []]
    (cond
      (and (nil? node) (empty? stack))
      acc

      node
      (recur (:left node)
             (conj stack node)
             acc)

      :else
      (let [processed-node (first stack)
            remaining-stack (rest stack)]
        (recur (:right processed-node)
               remaining-stack
               (conj acc (:value processed-node)))))))

(in-order-tail record-tree) ;; [4,2,6,5,7,1,3,9,8]


;; continuation passing style approach
(defn in-order-cps [node acc k]
  (if (nil? node)
    #(k acc)
    (fn []
      (in-order-cps
       (:left node)
       acc
       (fn [left-acc]
         (in-order-cps (:right node) (conj left-acc (:value node)) k))))))

(trampoline in-order-cps record-tree [] identity) ;; [4,2,6,5,7,1,3,9,8]
