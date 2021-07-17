(ns the-little-schemer.chapter-1
  (:require [the-little-schemer.utils :as utils]))


(assert
 (and

  (utils/atom? 'atom)
  (utils/atom? 'turkey)
  (utils/atom? 1942)

  (list? '(atom))
  (list? '(atom turkey or))

  ;; xyz is an S-expression
  ;; or symbolic expression https://en.wikipedia.org/wiki/S-expression

  (= (first '(a b c)) 'a)

  (= (rest '(a b c)) '(b c))

  (= (cons 'a '(b c)) '(a b c))

  (utils/null? '())

  ))
