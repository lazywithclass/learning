(ns the-little-schemer.chapter-2
  (:require [the-little-schemer.utils :as utils]))


(assert
 (and

  (utils/lat? '(bacon and eggs))
  (not (utils/lat? '(bacon (and) eggs)))

  (utils/member? 'bacon '(bacon and eggs))
  (not (utils/member? 'jelly '(bacon and eggs)))

  ))
