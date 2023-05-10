## Typechecker integer list

An expression is defined as such `e ::= k | nil | cons(e1,e2) | hd e | tl e`

where `k` is an int constant.

``` f#
type tm =
  K of int
  | Nil
  | Cons of tm * tm
  | Hd of tm
  | Tl of tm
```

``` f#
type tp = INT | L | E | C 

```

where:

`INT` is the type for an int constant
`L`   is the type for a list
`E`   is the type for the empty list
`C`   is the type for a non empty list (has at least one Cons)

Write a type checker with the follow rules
 * a constant has type `INT`
 * the empty list has type `E`
 * `Cons(e1,e2)` has type `C` if `e1` has type INT and `e2` has type `L`, or `E`, or `C`
 * `(Hd e1)` has type INT if `e1` has type `C`
 * `(Tl e1)` has type L if `e1` has type `C`
