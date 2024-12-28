---
tags:
  - scott-wlaschin
  - functional-programming
  - monadic-bind
  - monad
  - continuations
  - monoid
---

<iframe width="560" height="315" src="https://www.youtube.com/embed/E8I19uA-wGY?si=v1I2KoaIkGDveHaX" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

"Types are not classes", "types are the set of inputs or outputs to a function".

"Things that are illegal in the real world are not compilable in your world".

## Parameterise all the things

Every function is a one-parameter-function, so that it could be connected via input and output. -> Partial application!

## Bind

Monadic bind

```ocaml
let bind f = function 
    | Some v -> f v
    | None   -> None

let example input = 
    doSomething input
    |> bind doSomethingElse
    |> bind doAThirdThing
    |> bind (fun z -> Some z)

(* instead of *)

let example input =
    let x = doSomething input
    if x <> null then
        let y = doSomethingElse x
        if <> null then
            let z = doAThirdThing y
            if z <> null then
                let result = z
                result
            else
                null
        else 
            null
    else
        null
```

But even with early returns that's not always the solution, it might be the case where you need the previous result.

Monads as a mean for continuation chaining.

## Monoid

![monoid.png](monoid.png)

[Why are monoids useful?](https://www.youtube.com/watch?v=E8I19uA-wGY&t=3248s)