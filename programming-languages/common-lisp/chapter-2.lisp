(defun lat? (lat)
  (cond
    ((null lat) T)
    ((atom? (car lat)) (lat? (cdr lat)))
    (T NIL)))

(lat? '(Jack Sprat could eat no chicked fat)) ; T
(lat? '((Jack) Sprat could eat no chicked fat)) ; NIL
(lat? '()) ; T
(lat? '(bacon (and eggs))) ; NIL


(defun member? (a lat)
  (cond
    ((null lat) NIL)
    ((eq (car lat) a) T)
    (T (member? a (cdr lat)))))

(member? 'poached '(fried eggs and scrambled eggs)) ; NIL
(member? 'poached '(fried eggs and poached eggs)) ; T
