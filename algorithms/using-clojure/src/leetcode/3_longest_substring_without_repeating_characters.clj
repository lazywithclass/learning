(ns leetcode.3-longest-substring-without-repeating-characters
  (:import [clojure.lang PersistentVector]))


;; Given a sequence, find the length of the longest subsequence without duplicate characters.

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


(longest-substr [\a \b \c \a \b \c \b \b]) ;; [\a \b \c]

(longest-substr [\b \b \b \b]) ;; [\b]

(longest-substr [\p \w \w \k \e \w]) ;; [\w \k \e]

(longest-substr [\a \b \c]) ;; [\a \b \c]

(longest-substr [\c]) ;; [\c]

(longest-substr []) ;; []
