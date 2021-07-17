(ns the-little-schemer.chapter-3
  (:require [the-little-schemer.utils :as utils]))


(assert
 (and

  (= (utils/rember 'a '(a b c)) '(b c))
  (= (utils/rember 'a '(b a c)) '(b c))

  (= (utils/firsts '((a 1 2 3) (b 4 5 6) (c 7 8 9))) '(a b c))

  (= (utils/insertR 'jalapenho 'and '(tacos tamales and salsa)) '(tacos tamales and jalapenho salsa))

  (= (utils/insertL 'jalapenho 'and '(tacos tamales and salsa)) '(tacos tamales jalapenho and salsa))

  (= (utils/subst 'c 'f '(a b f d)) '(a b c d))

  (= (utils/subst2 'c 'f 'g '(a b f d)) '(a b c d))
  (= (utils/subst2 'c 'f 'g '(a b g d)) '(a b c d))

  (= (utils/multirember 'a '(a a a)) '())

  (= (utils/multiinsertR '1 '0 '(0 0 0)) '(0 1 0 1 0 1))

  (= (utils/multiinsertL '0 '1 '(1 1 1)) '(0 1 0 1 0 1))

  (= (utils/multisubs '42 '0 '(0 0 0)) '(42 42 42))

  ))
