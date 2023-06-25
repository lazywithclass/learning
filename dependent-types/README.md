## Dependent types

DISCLAIMER: nothing here should be thought as correct. Do not follow what I write here. I am studying the subject, expect errors.


### What are dependent types?


A language is called dependently typed if it allows for functions from values to types. 
Or in other words, parameterizing a type definition over a value:

```
type BoundedInt(n) = {i:Int | i<=n}

def min(i : Int, j : Int) : BoundedInt(j) =
  if i < j then i else j
```

[Source](https://stackoverflow.com/a/9374698/57095)


#### Refinement types

Refinement types indeed are a simple form of dependent types.
