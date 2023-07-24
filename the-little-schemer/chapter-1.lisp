(defun atom? (x)
  (not (listp x)))

;; S-expression are atoms and lists

(atom? '()) ; NIL
(null '()) ; T
(eq 'Harry 'Harry) ; T
