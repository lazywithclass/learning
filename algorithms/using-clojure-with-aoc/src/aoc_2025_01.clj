(ns aoc-2025-01
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.java.io :as io]))


(defn- move
  "a move L that exceeds 0 is a movement L that starts from 99
   a move R that exceeds 99 is a movement R that starts from 0"
  ([pos dir clicks-left]
   (move pos dir clicks-left 0 true))
  ([pos dir clicks-left zero-counts ignore]
   (let [zero-counts (if (and (not ignore) (= pos 0)) (inc zero-counts) zero-counts)]
     (match [pos dir clicks-left]
            [_   _   0] {:pos pos :cnt zero-counts}
            [0   \L  _] (recur 99      dir (dec clicks-left) zero-counts false)
            [p   \L  _] (recur (dec p) dir (dec clicks-left) zero-counts false)
            [99  \R  _] (recur 0       dir (dec clicks-left) zero-counts false)
            [p   \R  _] (recur (inc p) dir (dec clicks-left) zero-counts false)))))


(move 50 \R 1)
(move 50 \R 2)
(move 50 \L 1)
(move 50 \L 2)
;; the following should return {:pos 50 :cnt 10}
(move 50 \R 1000)


;; (defn- move
;;   "a move L that exceeds 0 is a movement L that starts from 99
;;    a move R that exceeds 99 is a movement R that starts from 0"
;;   [dir pos amount]
;;   (match [dir pos amount]
;;          [_   _   0] pos
;;          [\L  0   a] (recur dir 99 (dec a))
;;          [\L  p   a] (recur dir (dec p) (dec a))
;;          [\R  99  a] (recur dir 0  (dec a))
;;          [\R  p   a] (recur dir (inc p) (dec a))))


(defn moves [start-pos instructions]
  (reduce (fn [[tot pos-count] [dir amount]]
            (let [{pos :pos cnt :cnt} (move (:pos pos-count) dir amount)]
              (println "Moving from" (:pos pos-count) "to" pos "with" cnt "zero-crossings, total so far" tot)
              [(+ tot cnt) {:pos pos}]))
          [0 {:pos start-pos :cnt 0}]
          instructions))


;; (defn moves [start-pos instructions]
;;   (reduce (fn [[res pos] [dir amount]]
;;             (if (= (move dir pos amount) 0)
;;               [(inc res) (move dir pos amount)]
;;               [res (move dir pos amount)]))
;;           [0 start-pos]
;;           instructions))


(defn parse-input [s]
  (map (fn [line]
         (let [[_ dir amount] (re-matches #"(L|R)(\d+)" line)]
           [(first dir) (Integer/parseInt amount)]))
       (str/split-lines s)))


(println "ASDASDASD")
(->> "aoc_2025_01.input"
     io/resource
     slurp
     parse-input
     (moves 50)
     first
     println)
