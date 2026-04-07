(ns core-async-flow-spike.simple
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]])
  (:gen-class))


;; >!  <!  parking put and parking get
;; >!! <!! blocking put and blocking get


(def echo-chan (chan))

(go (println (<! echo-chan)))

(>!! echo-chan "Hello, world!")


(def echo-buffer (chan 2))
(>!! echo-buffer "Hello, buffer!")
(>!! echo-buffer "Hello, buffer, again!")

(>!! echo-buffer "Hello, buffer, blocked!")
