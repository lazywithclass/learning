(ns transducers)


;; --- DISCLAIMER ---
;; this is for myself rather than for anyone else, if you want what transducers are and how to use them,
;; there are many good resources out there, for example: https://clojure.org/reference/transducers
;; --- DISCLAIMER ---


;; --- Data ---

(def nums (range 1 11))

;; --- Traditional approach  ---

(->> nums
     (filter odd?)
     (map #(* % %))
     (take 3))

;; 3 intermediate sequences are created: (1 3 5 7 9), (1 9 25 49 81), and (1 9 25 49 81) again (because of take).

;; --- Transducers ---

(def transducer
  (comp ;; take care that comp order is left to right, not right to left as the usual function composition
   (filter odd?)
   (map #(* % %))
   (take 3)))

(transduce transducer
           ;; what should be done with each transduced element
           ;; should support 0, 1, 2 arity
           (fn [& args] (println args) (apply conj args))
           []   ;; the initial value for the reduction
           nums)

;; this prints
;; ([] 1)
;; ([1] 9)
;; ([1 9] 25)
;; ([1 9 25])



(def counter (atom 0))

(defn log-step [step-name x]
  (swap! counter inc)
  (println (str step-name ":\t" x)))

(transduce
 (comp
  (filter (fn [el] (log-step "filter" el) (odd? el)))
  (map    (fn [el] (log-step "map" el)    (* el el)))
  (map    (fn [el] (log-step "map2" el)   (inc el))))
 (fn [& args] (log-step "red-fn" args) (apply conj args)) [] nums)

counter

;; filter:	1
;; map:	1
;; map2:	1
;; red-fn:	([] 2)
;; filter:	2
;; filter:	3
;; map:	3
;; map2:	9
;; red-fn:	([2] 10)
;; filter:	4
;; filter:	5
;; map:	5
;; map2:	25
;; red-fn:	([2 10] 26)
;; filter:	6
;; filter:	7
;; map:	7
;; map2:	49
;; red-fn:	([2 10 26] 50)
;; filter:	8
;; filter:	9
;; map:	9
;; map2:	81
;; red-fn:	([2 10 26 50] 82)
;; filter:	10
;; red-fn:	([2 10 26 50 82])

;; comp on transducers builds a pipeline of decorators around the reducing function, not
;; a pipeline of sequences. Each element descends through the entire pipeline in one shot.

;; --- "We have transducers at home" ---

;; here I am trying to roll my own implementation

;; a transducer has to
;; - take a reducing function and return a new reducing function
;; - the new reducing function has to support 0, 1, and 2 arity

;; taken form the docs
;; (fn [rf]
;;   (fn ([] ...)
;;       ([result] ...)
;;       ([result input] ...)))


;; filter transducer
(defn my-filter [pred]
  (fn [rf]
    (fn
      ([]      (rf))
      ([acc]   (rf acc))
      ([acc x] (if (pred x) (rf acc x) acc)))))


;; map transducer
(defn my-map [f]
  (fn [rf]
    (fn
      ([]      (rf))
      ([acc]   (rf acc))
      ([acc x] (rf acc (f x))))))


;; (defn my-transduce [xf rf init xs]
;;   (loop [x   (first xs)
;;          acc init]
;;     (if (nil? x)
;;       acc
;;       (let [reduced (xf acc x)]
;;         (recur (rest xs)
;;                (rf acc reduced))))))


(defn my-transduce [xf rf init xs]
  ;; compose: xf wraps rf into a new reducing fn
  (let [rf* (xf rf)]
    (loop [acc init
           xs  xs]
      (if (empty? xs)
        (rf* acc) ; completion arity — flush any state
        (let [acc' (rf* acc (first xs))]
          (if (reduced? acc') ; respect early termination
            (rf* (unreduced acc'))
            (recur acc' (rest xs))))))))


(my-transduce
 (comp
  (my-filter (fn [el] (log-step "filter" el) (odd? el)))
  (my-map    (fn [el] (log-step "map" el)    (* el el)))
  (my-map    (fn [el] (log-step "map2" el)   (inc el))))
 (fn [& args] (log-step "red-fn" args) (apply conj args)) [] nums)
