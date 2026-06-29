
[https://www.youtube.com/watch?v=2GfFlfToBCo](Tutorial Tuesday #1: Intro to continuations, call/cc, and CPS)

## Continuations

Continuation = "The rest of the work that has to happen to finish evaluation of your program"

Instead of representing the rest of the computation in the stack, we represent it in the heap.

  

Continuations allow you to

 * get your hands on the control flow of a program

 * simulate exceptions, loops


## CPS

Continuation Passing Style
  

`(f v) = (fC v id)` where `f` is the function, `v` the value, `fC` the lifted function to work with continuations, `id` the identity function.

TODO VAI A VEDERE ALLINIZIO DOVE DICE CHE CON L'ACC NON E' GARANTITO ... QUALCOSA

https://fsharpforfunandprofit.com/posts/recursive-types-and-folds/

## APS - Accumulator Passing Style

  

The recursive call is in tail position and we pass the result of the partial computation into an accumulator

  
```ocaml

let rec fact n =

     match n with

         | 0 -> 1

         | _ -> n * (fact (n - 1))_

// call it like this

fact 5

  

let rec factAps n acc =

     match n with

         | 1 -> acc

         | _ -> factAps (n - 1) (acc * n)

// call it like this

factAps 5 1

```

  

The shape that the calls form could be seen at https://youtu.be/2GfFlfToBCo?t=4939, along with a comparation of the two approaches. `fact` accumulates on the stack

so we could run out of space very quickly.

  

```ocaml

let rec factCps n k =

     match n with

         | 0 -> k 1

         | _ -> factCps (n - 1) (fun v -> k (v * n))

// call it like this

// where id is the identity function, a function that when called with an argument it returns that argument

factCps 5 id

```

  

Again the shape is like `factAps`'s.

And the interesting fact is that you can start off with a continuation that resumes from a previous computation, not necessarily from the beginning.

  
