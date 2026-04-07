(defun member? (a lat)
  (cond
    ((null lat) nil)
    ((eq (car lat) a) T)
    (T (member? a (cdr lat)))))

(member? 42 '(40 41 42 43))
(member? 24 '(40 41 42 43))


(defun set-union (set1 set2)
  (cond
    ((null set1) set2)
    ((not (member? (car set1) set2))
     (cons (car set1) (set-union (cdr set1) set2)))
    (T (set-union (cdr set1) set2))))

(set-union '(1 2 3) '(2 3 4))
(set-union '(1 2 3) '(1 2 3))
