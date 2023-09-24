## S-expressions

A s-expression (sexp) is a common format for data serialization.
It consists of an arbitrary atom  or of a pair of sexp,
which are represented as a paranthesized pair
(usually separated by a dot in concrete syntax):

'a sexp ::= 'a | ('a sexp . 'a sexp)

For example

("this" . (("is" . "a") . ("s" . "expression")))

is a sexp of strings.

((1 . 5) . 2)

is a sexp of int. 

We use this declaration:

type 'a sexp = Atom of 'a | Pair of ('a sexp * 'a sexp)

1. Write a function `serialize : 'a list -> string sexp`

that serializes a list into a term of type "string sexp".

Attention:
- the empty list is serialized by the string "nil"
- cons is serialized by the string "cons" 
- each term x of the list must be converted into a string.

For example:

``` f#
let s1 =  serialize [1;2] 
// Pair
//    (Atom "cons",
//      Pair (Atom "1", Pair (Atom "cons", Pair (Atom "2", Atom "nil"))))
```

2 Write the inverse function `unser :   string sexp -> string list`

For example:

``` f#
let u1 = unser s1
// val u1 : string list = ["1"; "2"]
```

Note that the result of unser is always a  list of string.

3. Write a Fscheck property that validates that serializing and then 
unserializing a string list xs yields the the same list xs.

Hint: you will have to exclude the empty and null string from xs

4. Write a foldBack-like function 

`sfoldB : ('a -> 'a -> 'a) -> ('b -> 'a) -> 'b sexp -> 'a`

and use it to compute the number of atoms in a sexp

`count : 'a sexp -> int`

5. Now, we change the represenetation of sexp so that they are typed.
As in the case of lists, we require that in a typed sexp all atoms
must have the same type.

To represent a typed sexp we introduce the type

`type 'a tsexp = Atom of char * 'a | Pair of ('a tsexp * 'a tsexp)`

where an Atom carries a tag (here a char) for its type.

For simplicity, we allow only the types INT, FLOAT and STRING: 

`type ty = INT | FLOAT | STRING`

The corresponding tags are:

INT      --->  'i'
FLOAT    --->  'f'
STRING   --->  's'

Example:

let isat = Pair(Atom('s', "is"),Atom('s', "a")) // tsexp of string
let onetwo = Pair(Atom('i', 1),Atom('i', 2))    // tsexp of int
let ill = Pair(Atom('i', "1"),Atom('s', "1"))   

The last one is ill-typed since it contains an atom with INT tag  and an atom with STRING tag,
thus it shoud be rejected by the type checker


The type checker satisifes these rules

```
  tag c corresponds to type t
---------------------------------
     Atom(c,_) : t


e1 : t         e2 : t
-----------------------
  Pair(e1,e2) : t
```

 Write a function `tyck : 'a tsexp -> ty option`

that implements the above rules

Examples:

``` f#
let t1 = tyck isat
// val t1 : ty option = Some STRING
let t2 = tyck onetwo
// val t2 : ty option = Some INT
let t3 = tyck ill
// val t3 : ty option = None_*
```

