(defun rember (a lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) a) (cdr lat))
    (T (cons (car lat)
             (rember a (cdr lat))))))

(rember 'mint '(lamb chops and mint jelly)) ; '(lamb chops and jelly)


(defun insertR (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old) (cons old (cons new lat)))
    (T (cons (car lat) (insertR new old (cdr lat))))))

(insertR 'e 'd '(a b c d f g d h)) ; '(a b c d e f g d h)


(defun insertL (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old) (cons new (cons old lat)))
    (T (cons (car lat) (insertL new old (cdr lat))))))

(insertL 'e 'f '(a b c d f g d h)) ; '(a b c d e f g d h)


(defun subst (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old) (cons new (cdr lat)))
    (T (cons (car lat) (cdr lat)))))

(subst 'topping 'fudge '(ice cream with fudge for dessert)) ; '(ice cream with topping for dessert)


(defun subst2 (new o1 o2 lat)
  (cond
    ((null lat) lat)
    ((or (eq (car lat) o1)
         (eq (car lat) o2)) (cons new (cdr lat)))
  (T (subst2 new o1 o2 (cdr lat)))))

(subst2 'vanilla 'chocolate 'banana '(banana ice cream with chocolate topping)) ; '(vanilla ice cream with chocolate topping)


(defun multirember (a lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) a) (multirember a (cdr lat)))
    (T (cons (car lat) (multirember a (cdr lat))))))

(multirember 42 '(42 a 42 b 42 c))


(defun multiinsertR (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old)
     (cons old (cons new (multiinsertR new old (cdr lat)))))
    (T
     (cons old (multiinsertR new old (cdr lat))))))

(multiinsertR 42 'a '(a a a)) ; '(a 42 a 42 a 42)


(defun multiinsertL (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old)
     (cons new (cons old (multiinsertL new old (cdr lat)))))
    (T
     (cons new (multiinsertL new old (cdr lat))))))

(multiinsertR 'a 42 '(42 42 42)) ; '(42 a 42 a 42 a)


(defun multisubst (new old lat)
  (cond
    ((null lat) lat)
    ((eq (car lat) old) (cons new (multisubst new old (cdr lat))))
    (T (multisubst new old (cdr lat)))))

(multisubst 'a 42 '(42 42 42)) ; '(a a a)
