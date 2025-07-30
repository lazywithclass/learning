(defun is-first? (a lat)
  (cond
    ((null lat) nil)
    (T (eq (car lat) a))))

(is-first? 'a '(a b a))

(defun two-in-a-row? (lat)
  (cond
    ((null lat) nil)
    (T (or
        (is-first? (car lat) (cdr lat))
        (two-in-a-row? (cdr lat))))))

(two-in-a-row? '(a b b c))
(two-in-a-row? '(a b c d))


(defun two-in-a-row? (lat)
  (cond
    ((null lat) nil)
    (T (is-first? (car lat) (cdr lat)))))

(defun is-first? (a lat)
  (cond
    ((null lat) nil)
    (T (or
        (eq (car lat) a)
        (two-in-a-row? lat)))))

(two-in-a-row? '(a b b c))
(two-in-a-row? '(a b c d))


(defun two-in-a-row-b? (preceding lat)
  (cond
    ((null lat) nil)
    (T (or
        (eq (car lat) preceding)
        (two-in-a-row-b? (car lat) (cdr lat))))))

(two-in-a-row-b? nil '(a b b c))
(two-in-a-row-b? nil '(a b c d))


(defun two-in-a-row? (preceding lat)
  (cond
    ((null lat) nil)
    (T (two-in-a-row-b? (car lat) (cdr lat)))))

(two-in-a-row? nil '(a b b c))
(two-in-a-row? nil '(a b c d))


(defun sum-of-prefixes-b (sum-so-far tup)
  (cond
    ((null tup) '())
    (T (cons
        (+ sum-so-far (car tup))
        (sum-of-prefixes-b (+ sum-so-far (car tup)) (cdr tup))))))

(defun sum-of-prefixes (tup)
  (sum-of-prefixes-b 0 tup))

(sum-of-prefixes '(1 1 1 1 1))


(defun multirember-f (test?)
  (lambda (a lat)
    (cond
      ((null lat) '())
      ((test? (car lat) a)
       ((multirember-f test?) a (cdr lat)))
      (T (cons (car lat)
                 ((multirember-f test?) a (cdr lat)))))))
