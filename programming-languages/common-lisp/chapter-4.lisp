(defun add1 (n)
  (+ n 1))


(defun sub1 (n)
  (- n 1))


(defun zero? (n)
  (= 0 n))

(zero? 0) ; T
(zero? 1) ; NIL


; define plus in terms of add1 and sub1
(defun plus (n m)
  (cond
    ((zero? n) m)
    (T (plus (sub1 n) (add1 m)))))

; the book suggests the following
; I think I prefer my solution as it doesn't increase the stack
(defun plus-book (n m)
  (cond
    ((zero? m) n)
    (T (add1 (plus-book n (sub1 m))))))

(plus 3 4) ; 7
(plus-book 3 4) ; 7


; I skipped the variations over plus


(defun addtup (tup)
  (cond
    ((null tup) 0)
    (T (+ (car tup) (addtup (cdr tup))))))

(addtup '(1 2 3 4)) ; 10


(defun addtup2 (tup1 tup2)
  (cond
    ((null tup1) tup1)
    ((null tup2) tup2)
    (T (cons (+ (car tup1) (car tup2)) (addtup2 (cdr tup1) (cdr tup2))))))

(addtup2 '(1 2 3) '(3 2 1)) ; '(4 4 4)


; I skipped the other functions as they were simple variations of what I already did
