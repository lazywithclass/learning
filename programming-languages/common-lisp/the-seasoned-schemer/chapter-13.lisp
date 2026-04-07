                                        ; (load "../quicklisp.lisp")
                                        ; (quicklisp-quickstart:install)
(load "/home/lazywithclass/quicklisp/setup.lisp")
(ql:add-to-init-file)
(ql:quickload :cl-cont)


(defun member? (a lat)
  (cond
    ((null lat) nil)
    ((eq (car lat) a) T)
    (T (member? a (cdr lat)))))

(defun intersect (set1 set2)
  (cond
    ((null set1) nil)
    ((member? (car set1) set2)
     (cons (car set1) (intersect (cdr set1) set2)))
    (T (intersect (cdr set1) set2))))

(intersect '(1 2 3) '(2 3 4))


;; looks like Common Lisp has letrec, but it's called labels

(labels ((fact (n)
           (if (zerop n)
               1
               (* n (fact (- n 1))))))
  (fact 10))


(defun intersect (set1 set2)
  (labels ((I (set)
             (cond
               ((null set) nil)
               ((member (car set) set2)
                (cons (car set) (I (cdr set))))
               (t (I (cdr set))))))
    (I set1)))

(intersect '(1 2 3) '(2 3 4))

;; mimicking call with continuations

(defun intersectall (lset)
  (cl-cont:with-call/cc
    (cl-cont:let/cc skip
      (labels
          ((A (lset)
             (cond
               (null (car lset) (skip '()))
               (null (cdr lset) (car lset))
               T (intersect (car lset) (A (cdr lset))))))))))

;; do I really need cl-cont though? Can't I do without it?


(defun rember-beyond-first (a lat)
  (labels ((R (l)
             (cond
               ((null l) nil)
               ((eq (car l) a) nil) ; this nil allows to ignore everything else
               (T (cons (car l) (R (cdr l)))))))
    (R lat)))

(rember-beyond-first 42 '(1 2 42 3 42 4 42 5)) ; '(1 2)
(rember-beyond-first 42 '(42 1 2 42 3 42 4 42 5)) ; '()


(defun rember-upto-last (a lat)
  (cl-cont:with-call/cc
    (cl-cont:let/cc skip
      (labels ((R (l)
                 (cond
                   ((null l) nil)
                   ((eq (car l) a) (skip (R (cdr l))))
                   (t (cons (car l) (R (cdr l)))))))
        (R lat)))))


(defun rember-upto-last-alt (a lat)
  (cl-cont:with-call/cc
    (labels ((R (l cont)
               (cond
                 ((null l) (funcall cont nil))
                 ((eq (car l) a)
                  ;; Skip this element - continue with rest but don't include it
                  (R (cdr l) cont))
                 (t (R (cdr l)
                       (lambda (rest)
                         (funcall cont (cons (car l) rest))))))))
      (cl-cont:let/cc exit
        (R lat exit)))))


(defun rember-upto-last (a lat)
  (labels ((R (l)
             (cond
               ((null l) nil)
               ((eq (car l) a)
                (cl-cont:with-call/cc
                  (cl-cont:let/cc skip
                    ;; Jump to skip with the result of processing the rest
                    (skip (R (cdr l))))))
               (t (cons (car l) (R (cdr l)))))))
    (R lat)))

(rember-upto-last 42 '(1 2 3 42 4 5 42 'hey))
