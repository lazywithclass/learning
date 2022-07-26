# Logic programming

Programming with Twelf is programming with nothing.

Functional programming refinement.

Requires a debugger to understand how expressions are evaluated.

## Basics

In logic programming we only specify what is true.

Judgments and proof: if all judgements hold then we can arrive to the conclusion through an inference rule. 

```
J1...Jn
------- R
   J
```

It reads as "If J1 and ... and Jn then we can conclude J by virtue of of rule 
R"; where R is the name of the inference rule, J1...Jn are the premises of the
rule, J is the conclusion, and the whole thing is a proof.

In logic programing we start from J and grow a proof tree upwards.

Remember to evaluate rules from left to right and from the bottom up, so for example here I want to find an ancestor
such that `ancestor adam Y`holds:

```
parent adam Z    ancenstor Z Y
----------------------------- anc
       ancestor adam Y
```

1) first I need to find a `Z` such that `adam` is `Z`'s parent (`parent adam Z` on the left)
2) now that I have `Z` I look for an `Y` such that `Z`is `Y`'s ancestor (`ancestor Z Y` on the right)

top-down or goal-directed means that we work backwards from the conjecture following the proof tree upwards.
bottom-up or forward-reasoning means that we work forwards from the axioms applying rules towards the conjecture.
(even though this is *so* counter-intuitive)

### Substitution

A substitution is a list of assignments in the form `X = t`, where `X` is a variable and `t` a term in the language of the program.

Computation happens through substitutions, which are equalities.

Since we don't want circular substitutions we can change a rule variables' considering what's called as a variant of the rule.

Substitutions happen simultaneously.

### Unification

Given a goal `G` and a goal `H` (in a rule `r`), a unifier between `G` and `H` is a substitution
`s` such that `Gs = Hs`.

The interesting point is that I can use `r` to prove `Gs`, so I can use "the other one".

### Terminology:

 * original goal is the query 
 * unknowns are logic variables
 * result of the computation is an answer substitution (substitutes logic variables, suppressing the proof)

Since functions are relations one is free to consider them from an input to an output, and can think about them for example from an output to an input.

## Twelf

(I've written a [Docker container for Twelf](https://github.com/lazywithclass/twelf-docker) to make it easier to use the language)

Twelf does not have types. DOES NOT HAVE TYPES. You have to define stuff like true and false.

A logic program is composed by clauses.<br />
All declarations should end with a `.`<br />
We don't have functions, but relations, and they are lowercase, variables are uppercase.<br />

"<-" is the implication. The following is read as "grandfather X Y, if father X Z and if parent Z Y" or "to prove that X is Y's grandfather we have to find Z such that 
X is Z's father and Z is the parent"

``` twelf
gf: grandfather X Y  % head of the clause
    <- father X Z    % body of 
    <- parent Z Y.   % the clause
```

Where
* `gf` is a label identifying the clause, it could have a `/` to represent different scenarios (for example `plus/z` and `plus/s` below)
* `grandfather X Y` is the head (or the name) of the clause, what follows is the body

It could also be seen as a rule captured by the following

```
parent Z Y   father X Z
------------------------ R
    grandfather X Y
```

Generally speaking we have

```twelf
c : H <- B1
      <- B2
      ...
      <- Bn
```

Read as "H if B1 if B2 ... if Bn" or "If B1 B2 ... Bn hold then H".

Where `c` is a label identifying the clause, `H` is an atomic formula (head of c), B1, ..., Bn are atomic formulas (body of c)

``` twelf
nat : type.
% zero
z : nat.
s : nat -> nat.

% relation declaration
plus : nat -> nat -> nat -> type.
% two axioms of the sum
% 0 + y = y
% clause
plus/z : plus z Y Y
% if X + Y = Z then (succ X) + Y = (succ Z)
% clause
plus/s : plus (s X) Y (s Z)
          <- plus X Y Z
```

`plus` is a binary relation, that binds two `nat`

### Proof search: how the interpreter builds derivations to solve queries

Clauses have to be written in such a way so that the interpreter could build derivations.

I look for all rules that unify with my goal to understand how the interpreter will proceed;
careful to follow rules in the same order as they are written.

```
parent adam Z   ancestor Z Y
----------------------------- R
    ancestor adam Y
```

To find `Y` I first have to find a `Z` such that `parent adam Z`, then I have to find `Y` such that `ancestor Z Y`.

Example

Given the following query: `Q = plus (s z) (s z) Z` we start with the proof tree `T0 = Q`
 * Goal1 is `plus (s z) (s z) Z`
 * which unifies with 
```
 plus X Y Z1
-------------------- pluss
plus (s X) Y (s Z1)
```
having `u1 = [ X = z; Y = s z; Z = s Z1 ]` as substitutions

### Syntax

`nat : type.` - is a type declaration
`zero : nat.` - is a fact

## Emacs integration

Useful commands, especially `twelf-trace-break-all` to understand how the proof search is performed

`C-c C-d` - check declaration
`C-c C-c` - check configuration
`C-c C-u` - server display
`top` - in \*twelf server\* to evaluate queries
`M-x twelf-trace-trace-all` followed by `C-c C-d` - traces the proofs search tree followed by the interpreter
`M-x twelf-trace-break-all` followed by `C-c C-d` - stops after each step
