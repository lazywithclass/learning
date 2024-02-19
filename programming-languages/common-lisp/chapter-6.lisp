(defun atom? (x)
  (not (listp x)))


(defun numbered? (aexp)
  (cond
    ((atom aexp) (numberp aexp))
    (T (and
        (numbered? (car aexp))
        (numbered? (car (cdr (cdr aexp))))))))

(numbered? '(3 + (4 - 5)))
(numbered? '(2 * sausage))
(numbered? '(sausage + (4 - 5)))
(numbered? '(3 + (4 - mushroom)))


(defun 1st-sub-exp (aexp)
  ((car (cdr aexp))))

(defun 2nd-sub-exp (aexp)
  ((car (cdr (cdr aexp)))))

(defun operator (aexp)
  (car aexp))

(defun value (aexp)
  (cond
    ((atom? aexp) aexp)
    ((eq (operator aexp) '+)
     (+ (value (1st-sub-exp aexp))))
    ((eq (operator aexp) '-)
     (- (value (1st-sub-exp aexp))))))

(value (+ 1 3))

(value (+ 1 (* 3 2))
