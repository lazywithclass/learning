(defun rember (a lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) a) (cdr lat))
    (T (cons (car lat)
             (rember a (cdr lat))))))


(defun member? (a lat)
  (cond
    ((null lat) NIL)
    ((eq (car lat) a) T)
    (T (member? a (cdr lat)))))

(member? 1 '(3 4 1))
(member? 0 '(1 2 3))

(defun set? (lat)
  (cond
    ((null lat) T)
    ((member? (car lat) (cdr lat)) NIL)
    (T (set? (cdr lat)))))

(set? '(1 2 3 1))
(set? '(1 2 2 3))
(set? '(1 2 3))

(defun makeset (lat)
  (cond
    ((null lat) NIL)
    ((member? (car lat) (cdr lat)) (makeset (cdr lat)))
    (T (cons (car lat) (makeset (cdr lat))))))

(makeset '(1 2 3 1 3 2 1))

(defun subset? (set1 set2)
  (cond
    ((member? (car set1 set2)) (subset? (cdr set1) set2))
    T NIL))

;; with and
(defun subset? (set1 set2)
  (and ((member? (car set1 set2)) (subset? (cdr set1) set2)) T))

(defun eqset? (set1 set2)
  (cond
    ((and (null set1) (null set2)) T)
    ((member (car set1) set2)
     (eqset? (cdr set1 (rember (car set1) set2))))
    (T NIL)))

;; but better
(defun eqset? (set1 set2)
  (and (subset? (set1 set2))
   (subset? set2 set1)))

(eqset? '(1 2 3) '(3 1 2))

(defun intersect (set1 set2)
  (cond
    ((null set1) ())
    ((member (car set1) set2) (cons (car set1) (intersect (cdr set1) set2)))
    (T (intersect (cdr set1) set2))))

(intersect '(1 2 3) '(3 4 5))
(intersect '(1 2) '(3 4 5))

(defun u (set1 set2)
  (cond
    ((null set1) set2)
    ((member (car set1) set2) (union (cdr set1) set2))
    (T (cons (car set1) (u (cdr set1) set2)))))

(u '(1 2 3) '(4 5 6))

;; ----------------------------------------------------------
;; Relations here
;; ----------------------------------------------------------

(defun rel/fst (rel)
  (car rel))

(defun rel/snd(rel)
  (car (cdr rel)))

(defun rel/build (a b)
  (cons a (cons b ())))

(rel/fst (rel/build 'a 'b))
(rel/snd (rel/build 'a 'b))

(defun rel/revrel (rel)
  (cond
    ((null rel) ())
    (T (cons (rel/build (rel/snd (car rel)) (rel/fst (car rel))) (rel/revrel (cdr rel))))))

(rel/revrel '((8 a) (pumpkin pie) (got sick)))
