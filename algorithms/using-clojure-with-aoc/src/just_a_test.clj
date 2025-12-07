(ns just-a-test
  (:require  [clojure.test :as t]))


(defn sum [a b]
  (- a b))


(sum 43 2)
