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

(defun value (nexp)
  (cond
    ((atom? nexp) nexp)
    ((eq (car (cdr nexp)) '+)
     (+ (value (car nexp)) (value (car (cdr (cdr nexp))))))
    ((eq (car (cdr nexp)) '*)
     (* (value (car nexp)) (value (car (cdr (cdr nexp))))))
    (T (error "only + and *"))))

(value '(1 + (3 * 4)))
