## Language Design

### Operational semantics

https://www.youtube.com/watch?v=exhwykjH_z4

Used for 
 * specify a programming language
 * communicate language design idea, communicate among experts what are the ideas going on

### Example

A language called Arithmetic. Defining a language syntax means defining the set of programs present in the language.

#### "Mathy" way

```
i ∈ Z ⇒ i ∈ A
e ∈ A ⇒ Pred(e) ∈ A
e ∈ A ⇒ Succ(e) ∈ A
e1 ∈ A ∧ e2 ∈ A ⇒ Plus(e1, e2) ∈ A
e1 ∈ A ∧ e2 ∈ A ⇒ Mult(e1, e2) ∈ A
```

#### Inference rules

```
H1    H2    ...    Hn
______________________
         C
```

is like saying `H1 ∧ H2 ∧ ... ∧ Hn ⇒ C`

###
