(defun pick (i lat)
  (cond
    ((eq i 1) (car lat))
    (T (pick (- i 1) (cdr lat)))))

(pick 2 '(3 4 5))


(defun keep-looking (a i lat)
  (cond
    ((numberp i) (keep-looking a (pick i lat) lat))
    (T (eq a i))))

(defun looking (a lat)
  (keep-looking a (pick 1 lat) lat))

(looking 'caviar '(6 2 4 caviar 5 7 3))
(looking 'caviar '(6 2 grits caviar 5 7 3))

; (looking 'caviar '(7 1 2 caviar 5 6 3)) ; this keeps on going forever


; the most partial function
(defun eternity (x)
  (eternity x))


(defun will-stop (f)
  ; ?
  )

(defun last-try (x)
  (and (will-stop? last-try) (eternity x)))
