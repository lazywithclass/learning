(ns the-little-schemer.utils)


;; chapter-1

(defn atom? [x]
  "returns true if `x` is an atom"
  (not (seq? x)))

(def null?
  "returns true if `x` is nil of the empty list"
  (fn [x]
    (or
     (nil? x)
     (= () x))))


;; chapter-2

(defn lat? [l]
  (cond
    (null? l) true
    (atom? (first l)) (lat? (rest l))
    true false))

(defn member? [a l]
  (cond
    (null? l) false
    (= a (first l)) true
    true (member? a (rest l))))

;; chapter-3

;; The difference here is that when we deal with an `l` that is a list
;; of atoms, when we deal with a `lat` that is a list of s-expr

(defn rember [a lat]
  (cond
    (null? lat) ()
    (= (first lat) a) (rest lat)
    true (cons (first lat) (rember a (rest lat)))))

(defn multirember [a lat]
  (cond
    (null? lat) ()
    (= (first lat) a) (multirember a (rest lat))
    true (cons (first lat) (multirember a (rest lat)))))

(defn firsts [lat]
  (cond
    (null? lat) ()
    true (cons (first (first lat)) (firsts (rest lat)))))

(defn insertR [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons (first lat) (cons new (rest lat)))
    true (cons (first lat) (insertR new old (rest lat)))))

(defn insertL [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons new lat)
    true (cons (first lat) (insertL new old (rest lat)))))

(defn subst [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons new (rest lat))
    true (cons (first lat) (subst new old (rest lat)))))

(defn subst2 [new old1 old2 lat]
  (cond
    (null? lat) ()
    (or
     (= (first lat) old1)
     (= (first lat) old2)) (cons new (rest lat))
    true (cons (first lat) (subst2 new old1 old2 (rest lat)))))

(defn multisubs [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons new (multisubs new old (rest lat)))
    true (cons (first lat) (multisubs new old (rest lat)))))

(defn multiinsertR [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons (first lat) (cons new (multiinsertR new old (rest lat))))
    true (cons (first lat) (multiinsertR new old (rest lat)))))

(defn multiinsertL [new old lat]
  (cond
    (null? lat) ()
    (= (first lat) old) (cons new (cons (first lat) (multiinsertL new old (rest lat))))
    true (cons (first lat) (multiinsertL new old (rest lat)))))
