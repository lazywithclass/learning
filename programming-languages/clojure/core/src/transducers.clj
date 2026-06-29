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

;; multiple lazy seq layers are created (not eagerly realized collections),
;; which still implies per-step overhead and non-fused traversal

;; --- Transducers ---

(def transducer
  ;; although comp is right-to-left, the resulting transformation
  ;; processes elements left-to-right through the pipeline
  (comp (filter odd?)
        (map #(* % %))
        (take 3)))

(transduce transducer
           ;; the reducing function defines how to accumulate results
           ;; and should support 0, 1, and 2 arities
           ;; Note: this is a simplified reducer for demonstration purposes
           (fn [& args] (println args) (apply conj args))
           ;; the initial value for the reduction
           []
           nums)

;; which prints
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

;; last application prints
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

;; comp on transducers builds **a pipeline of decorators around the reducing function**, not
;; a pipeline of sequences
;; Each element flows through the entire pipeline before the next element is processed

;; --- "We have transducers at home" ---

;; here I am trying to roll my own implementation

;; a transducer has to
;; - take a reducing function and return a new reducing function
;; - the new reducing function has to support 0, 1, and 2 arity
;; taken from the docs, rf is the reducing function
;; (fn [rf]
;;   (fn ([] ...)
;;       ([result] ...)
;;       ([result input] ...)))

;; 0 arity: init
;; 1 arity: completion
;; 2 arity: step

;; filter transducer
(defn my-filter [pred]
  (fn [rf]
    (fn
      ([]      (rf))
      ([acc]   (rf acc))
      ([acc x] (if (pred x) (rf acc x) acc)))))

;; This is not continuation-passing style
;;
;; Transducers do not capture or reify control flow
;; Instead, they locally transform the reducing function by wrapping it
;;
;; Each step calls the next reducing function in the chain
;; Control flow (including early termination) is handled explicitly
;; via reduced, not by passing continuations around

;; map transducer
(defn my-map [f]
  (fn [rf]
    (fn
      ([]      (rf))
      ([acc]   (rf acc))
      ([acc x] (rf acc (f x))))))

(defn my-transduce [xf rf init xs]
  ;; xf transforms a reducing function into a new reducing function
  ;; it builds the transducer pipeline around rf
  (let [rf* (xf rf)]
    (loop [acc init
           xs  xs]
      (if (empty? xs)
        ;; gives the transducer a chance to flush internal state
        ;; important for stateful transducers like partition
        (rf* acc)
        (let [acc' (rf* acc (first xs))] ;; step
          (if (reduced? acc')
            ;; early termination:
            ;; - reduced signals "stop processing"
            ;; - unwrap it with unreduced
            ;; - but still call completion arity to finalize
            (rf* (unreduced acc'))
            (recur acc' (rest xs))))))))

(my-transduce
 (comp
  (my-filter (fn [el] (log-step "filter" el) (odd? el)))
  (my-map    (fn [el] (log-step "map" el)    (* el el)))
  (my-map    (fn [el] (log-step "map2" el)   (inc el))))
 (fn
   ([] (log-step "unused" []) [])
   ([acc] (log-step "final" acc) acc)
   ([acc x] (log-step "reduce" [acc x]) (conj acc x))) [] nums)

;; I will provide a justification of why this is useful in a later file
