---
cssclasses:
  - cornell-note
tags:c
  - databases
---

[[Relational algebra]]
[[PostgreSQL]]

# Relational model

Mathematical relations between sets (domains) via a Cartesian product.
Data is organized in rows, all rows are different from each other (since they are the result of a Cartesian product).

| $Attribute_1$ | $Attribute_2$ | $\dots$ | $Attribute_k$ |
| --- | --- | --- | --- |
| $Data_1$ | $Data_2$ | $\dots$ |$Data_k$ |

Keep the schema as fixed as possible, so that applications using it do not need to change accordingly.

Given $D_1 = \{a,b\}$ and $D_2 = \{x,y,z\}$ the Cartesian product is $D_1 \times D_2 = \{(a,x),(a,y),(a,z),(b,x),(b,y),(b,z)\}$.
A relation is a subset of that product such that $r \subseteq D_1 \times D_2 = \{(a,x), (a,y), (b,x), (b, y)\}$.

## NULL

Absence of a value in the domain.
We can't use it to compare values, an attribute $year$ which could be $NULL$ could not be compared with, for example, $2024$ to understand if it comes before or after. We can't even say if they're different or not (in a way).

### Open-world semantic

Everything that does not falsify a predicate it's true.

### Closed-world semantic

Only something that verify a predicate it's true.

## Constraints

A predicate that for each instance could return true or false.

### In a table

* domain constraints - for example $year \geq 1950 \ AND \ year \leq 2015$
* row constraints - express conditions on attributes on a row, domain constraints are row constraints that operate on a single attribute
* key constraints

## Logic model

