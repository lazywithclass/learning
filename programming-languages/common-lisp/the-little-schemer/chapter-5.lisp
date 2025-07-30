(defun atom? (x)
  (not (listp x)))


(defun rember* (a l)
  (cond
    ((null l) l)
    ((atom? (car l))
     (cond
       ((eq (car l) a) (rember* a (cdr l)))
       (T (cons (car l) (rember* a (cdr l))))))
     (T (cons (rember* a (car l)) (rember* a (cdr l))))))

(rember* 'cup '((coffee) cup ((tea) cup) (and (hick)) cup)) ; '((coffee) ((tea)) (and (hick)))
(rember* 'sauce '(((tomato sauce)) ((bean) sauce) (and ((flying)) sauce))) ; '(((tomato)) ((bean)) (and ((flying))))


(defun insertR* (new old l)
  (cond
    ((null l) l)
    ((atom? (car l))
     (cond
       ((eq (car l) old) (cons old (cons new (insertR* new old (cdr l)))))
       (T (cons (car l) (insertR* new old (cdr l))))))
     (T (cons (insertR* new old (car l)) (insertR* new old (cdr l))))))

(insertR* 1 'a '((a) b c ((((a b c (a))))))) ; '((a) b c ((((a b c (a))))))


(defun occur* (a l)
  (cond
    ((null l) 0)
    ((atom? (car l))
     (cond
       ((eq (car l) a) (+ 1 (occur* a (cdr l))))
       (T (occur* a (cdr l)))))
    (T (+ (occur* a (car l)) (occur* a (cdr l))))))

(occur* 'banana '((banana) (split ((((banana ice))) (cream (banana)) sherbet)) (banana) (bread) (banana brandy))) ; 5


(defun subst* (new old l)
  (cond
    ((null l) l)
    ((atom? (car l))
     (cond
       ((eq (car l) old) (cons new (subst* new old (cdr l))))
       (T (cons (car l) (subst* new old (cdr l))))))
    (T (cons (subst* new old (car l)) (subst* new old (cdr l))))))

(subst* 'orange 'banana '((banana) (split ((((banana ice))) (cream (banana)) sherbet)) (banana) (bread) (banana brandy)))
; ((orange) (split ((((orange ice))) (cream (orange)) sherbet)) (orange) (bread (orange brandy)))


; insertL* is the same as insertR*


(defun member* (a l)
  (cond
    ((null l) nil)
    ((atom? (car l))
     (cond
       ((eq (car l) a) T)
       (T (member* a (cdr l)))))
    (T (or (member* a (car l)) (member* a (cdr l))))))

(member* 'chips '((potato) (chips ((with) fish) (chips)))) ; T
(member* 'banana '((potato) (chips ((with) fish) (chips)))) ; nil


(defun leftmost (l)
  (cond
    ((null l) l)
    ((atom? (car l)) (car l))
    (T (leftmost (car l)))))

(leftmost '((potato) (chips ((with) fish) (chips)))) ; 'potato
(leftmost '(((((left)))) right)) ; 'left


; left out the other functions
