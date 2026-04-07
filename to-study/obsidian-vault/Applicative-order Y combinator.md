---
cssclasses: []
tags:
  - y-combinator
  - applicative-order-y-combinator
---
```table-of-contents
```

## Introduction

This started with [The Little Schemer](https://mitpress.mit.edu/9780262560993/the-little-schemer/) (which is also the source of all these examples), and it stumped me for more time than I like to admit.

All code is in [Scheme](https://www.scheme.org/).

### Why is it called like that?

#### Applicative order

Starting with an example helped me

* applicative order - evaluate arguments first, eager (Clojure) 
```language-clojure
(defn square [x] (* x x))

(square (+ 1 2)) ;; first evaluates (+ 1 2)
                 ;; then square is applied to 3
                 ;; then (* 3 3)
```

* normal order - apply function first, lazy (Haskell)
```language-haskell
square x = x * x 

square (1 + 2) ;; substitutes to (1 + 2) * (1 + 2)
               ;; then evaluates (1 + 2) only when * forces it
```

It's "normal" as in "lambda calculus normal", I thought the opposite of normal when I first heard about it.
#### Y

[The name came from Curry himself](https://en.wikipedia.org/wiki/Fixed-point_combinator#cite_note-4), I don't know if there's a reason besides just letters as for [the other combinators](https://en.wikipedia.org/wiki/Combinatory_logic#Examples_of_combinators).

#### Combinator

> A combinator is a [higher-order function](https://en.wikipedia.org/wiki/Higher-order_function "Higher-order function") that uses only [function application](https://en.wikipedia.org/wiki/Function_application "Function application") and earlier defined combinators to define a result from its arguments.

So no [free variables](https://en.wikipedia.org/wiki/Free_variables_and_bound_variables).

#### Well, actually...

It's called the Z combinator, because it's the one that works for eager languages, for lazy languages you would use the Y combinator.

## Derivation

### Starting step

Start with a length function defined for the empty list

```language-scheme
((lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l)))))))
 eternity)
```


`eternity` is a function that never returns.
This is the length function defined for a list of length 1, instead of eternity there's another instance of the function itself, allowing for an extra "iteration".
`eternity` is the value passed to `length` at the last "iteration".

```language-scheme
((lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l)))))))
 ((lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l)))))))
  eternity))
```

### Refactoring step

As this grows it's easier to spot the duplication.
Let's remove that.

The following 

```language-scheme
(lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l)))))))
```

receives the `length` function, but you could think of it as "being" the `length` function.
So let's receive it as parameter in a function and apply it to eternity.

```language-scheme
((lambda (make-length)
   (make-length eternity))
 (lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l))))))))
```

Now it's easier to show the `length` function for a list of length `<= 3`, we just need to prepare for such scenario in the first part

```language-scheme
((lambda (make-length)
   (make-length 
    (make-length
     (make-length
      (make-length eternity)))))
 (lambda (length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 (length (cdr l))))))))
```

### "Nobody cares what function we pass to make-length"

(pag. 165)

That is because at the last iteration we're going to following the `(null? l)` branch of the `cond`, because there are no more elements left in the list, so we don't care what we initially pass to `make-length`.

So we get

```language-scheme
((lambda (make-length)
   (make-length make-length))
 (lambda (make-length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 ((make-length make-length) 
                    (cdr l))))))))
```

So that we bootstrap at the beginning passing `make-length` to itself (as we said anything will do), and then we add one more step just before going on with recursion once more.
So we're expanding the `length` function on the fly when we find ourselves in the need "for one more".

### "It no longer contains the function that looks like `length`"

So we add another lambda-layer

```language-scheme
((lambda (make-length)
   (make-length make-length))
 (lambda (make-length)
   ((lambda (length)
      (lambda (l)
	    (cond
          ((null? l) 0)
          (else (add1 (length (cdr l)))))))
    (make-length make-length))))
```

But this never stops, because Scheme evaluates expressions in applicative order, so

```language-scheme
(f (g x))
```

means that `g` is applied to `x` and then `f` is applied onto the result.
So we use a thunk to get lazy evaluation.

First we start with the previous definition, and we add the `lambda` 

```language-scheme
((lambda (make-length)
   (make-length make-length))
 (lambda (make-length)
   (lambda (l)
     (cond
       ((null? l) 0)
       (else (add1 ((lambda (x) ((make-length make-length) x)) 
                    (cdr l))))))))
```

Then we move this new `lambda` out to get the `length` function

```language-scheme
((lambda (make-length)
   (make-length make-length))
 (lambda (make-length)
   ;; len function
   ((lambda (length)
     (lambda (l)
	   (cond
	     ((null? l) 0)
	     (else (add1 (length (cdr l)))))))
	;; len function
	(lambda (x) ((make-length make-length) x)))))
```

Now we extract the `length` function

```language-scheme
((lambda (len)
  ((lambda (make-length)
     (make-length make-length))
   (lambda (make-length)
     (len (lambda (x) ((make-length make-length) x))))))
 ;; pass the functional to the combinator
 (lambda (length) 
   (lambda (l) 
     (cond 
       ((null? l) 0) 
       (else (add1 (length (cdr l))))))))
```

And the first part is the Applicative-order Y Combinator.

The functional is like a "template" that says "given a `length` function, here's how to build a `length` function." Y finds the function that, when you plug it into that template, you get itself back out.
The functional isn't recursive, but it describes recursion; Y "ties the knot" and produces the actual recursive function.

### So, what do we got?

The outer

```language-scheme
(lambda (make-length)
  (make-length make-length))
```

produces the first `length` function while the inner one adds one more step right at the last moment, when we decided we need to recur one more time (at least) to find the result.


