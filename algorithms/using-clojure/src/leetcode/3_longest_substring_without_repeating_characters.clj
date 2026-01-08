(ns leetcode.3-longest-substring-without-repeating-characters
  (:import [clojure.lang PersistentVector]))


;; Given a sequence, find the length of the longest subsequence without duplicate characters.
;; (I've changed the text of the problem, from string to sequence)


;; following solution uses too much space
;; I also mistakenly returned the whole longest subseq, instead just the len is required
(defn longest-substr [^PersistentVector s]
  (loop [idx 0
         seen {}
         current []
         longest []]
    (let [element     (get s idx) ;; leverage get instead of nth for the nil benefit
          new-longest (if (> (count current) (count longest)) current longest)]
      (cond
        (= idx (count s))
        new-longest

        (seen element)
        (recur (inc (seen element)) {} [] new-longest)

        :else
        (recur (inc idx) (assoc seen element idx) (conj current element) longest)))))


;; optimized version for space
(defn longest-substr [^PersistentVector s]
  (loop [^long left 0
         ^long right 0
         seen {}
         ^long max-len 0]
    (let [element (get s right)]
      (if (nil? element)
        max-len
        (let [^long prev-idx (get seen element)
              ^long new-end  (if (and prev-idx (>= prev-idx left))
                         (inc prev-idx)
                         left)
              ^long new-len  (- right new-end -1)
              ^long new-max  (max max-len new-len)]
          (recur (inc right)
                 new-end
                 (assoc seen element right)
                 new-max))))))


(longest-substr [\a \b \c \a \b \c \b \b]) ;; [\a \b \c]

(longest-substr [\b \b \b \b]) ;; [\b]

(longest-substr [\p \w \w \k \e \w]) ;; [\w \k \e]

(longest-substr [\a \b \c]) ;; [\a \b \c]

(longest-substr [\c]) ;; [\c]

(longest-substr []) ;; []
