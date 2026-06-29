---
cssclasses:
  - cornell-note
tags:
  - parser-combinators
  - fsharp
  - combinators
---

<iframe width="560" height="315" src="https://www.youtube.com/embed/RDalzi7mhdY?si=d34sHPNeCdW-rYfi" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

Here's the [blog post](https://fsharpforfunandprofit.com/posts/understanding-parser-combinators/) the author made on the subject 

![Pasted image 20240112223842.png](Pasted%20image%2020240112223842.png)

```
Parser<A> combine-with Parser<B> => Parser<C>
              ^
          combinator
```

In traditional parsers there is a lexing stage, and a parsing stage, not in parser combinators.

A combinator is "a function that depends only on its inputs".

`reduce` is a function that given a sequence of things takes an operator and sticks it between every element in the list.