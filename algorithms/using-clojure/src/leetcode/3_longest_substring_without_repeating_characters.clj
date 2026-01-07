(ns leetcode.3-longest-substring-without-repeating-characters
  (:import [clojure.lang PersistentVector]))


;; Given a sequence, find the length of the longest subsequence without duplicate characters.


;; following solution uses too much space, O(n)
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


;; O(1) space
(defn longest-substr [^PersistentVector s]
  (loop [win-beg 0
         win-end 0
         seen {}
         max-len 0]
    (let [element (get s win-beg)]
      (if (nil? element)
        max-len
        (let [prev-idx (get seen element)
              new-end  (if (and prev-idx (>= prev-idx win-end))
                         (inc prev-idx)
                         win-end)
              new-len  (- win-beg new-end -1)
              new-max  (max max-len new-len)]
          (recur (inc win-beg)
                 new-end
                 (assoc seen element win-beg)
                 new-max))))))


(longest-substr [\a \b \c \a \b \c \b \b]) ;; [\a \b \c]

(longest-substr [\b \b \b \b]) ;; [\b]

(longest-substr [\p \w \w \k \e \w]) ;; [\w \k \e]

(longest-substr [\a \b \c]) ;; [\a \b \c]

(longest-substr [\c]) ;; [\c]

(longest-substr []) ;; []
