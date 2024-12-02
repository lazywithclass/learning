---
cssclasses:
  - cornell-note
tags:
  - databases
---

[[Relational algebra]]
[[PostgreSQL exercises]]

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

## Functional dependency

A dependency $FD: X \rightarrow \ Y$ means that the values of $Y$ are determined by the values of $X$.

"Which are the minimum attributes I need to determine that attribute?"

This allows to determine which attributes go in which tables.

Of course we could only recognize functional dependencies if we know the domain of the application, if we don't know anything it's very hard to do a good job in identifying them.

<aside>key concept</aside>

Key question to ask ourself to see if there's a functional dependency: "left side of this hypothetical dependency identifies precisely the right side?" if not then there isn't a functional dependency.

## Normal forms

We want to avoid redundancy in the information.

A database is well formed if it has 1NF, 2NF, 3NF.
As a rule of thumb:
* we want lots of writes $\rightarrow$ more normalization
* we want lots of reads $\rightarrow$ less normalization

### 1NF

Only atomic attributes.
We don't have a relational database if we don't have this NF.

### 2NF

Depends on 1NF.

Every non-key attribute must depend on the primary key, as a whole, and not just on a subset of it.
Normalization: put the subset of the key and the attribute in a new table.

If the key is a single attribute then we always have 2NF.

### 3NF

Depends on 2NF.

Transitive dependency: $X \rightarrow Y$ is transitive if a set of attributes $Z$ (not key and not subset of a key) exists such that $X \rightarrow Z$ and $Z \rightarrow Y$. 
2NF and no non-prime attribute in $R$ depends in a transitive way on the primary key.

In other words: we don't want two non-key attributes on the left AND on the right of a functional dependency. They all have to be on the left side.

### BCNF

All non prime attributes must depend on a super-key.

The key in the decomposed relations must be a super-key in the original relation. 

## Using normal forms to decompose relations

Goal is to start from a big universal relation and finish to a scheme with multiple relations, which respect the normal forms.

There is a procedure to follow, which has 3 rules:
* preserve attributes
* preserve functional dependencies
* lossless joins - we don't want joins to that create data that was not present in the original relations

The algorithm is:
1. define functional dependencies
2. minimize functional dependencies
3. for each functional dependency $X \rightarrow Y$, create a new relation where we have $X$ and all dependencies on the right side that have $X$ on the left side 
4. attributes left out go in a relation of their own

## Normalization

Anomalies and redundancies arise when we have functional dependencies like $X \rightarrow Y$, where $X$ is not a superkey (a subset of the key). 
To avoid redundancies and anomalies, we have to put our relation in BCNF. 


