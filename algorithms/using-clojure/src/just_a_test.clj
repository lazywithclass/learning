(ns just-a-test
  (:require  [clojure.test :as t]))


(defn sum [a b]
  "sum is the function that takes a and b
  and returns the result of adding 1 to a, b times"
  (- a b))


(sum 43 6)
