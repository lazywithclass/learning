---
cssclasses:
  - cornell-note
  - flex
tags:
  - relational-algebra
  - sql
---

Allows to understand 
 * fundamental knowledge about how queries work
 * execution plans and how to construct efficient ones

## Fundamental principle

<aside>one tuple at a time</aside>
We never have the chance to see all data together, we just see one tuple at a time. So we need to put data side by side to be able to compare it.
This is because the relations contain facts.
Understanding this allows to reason about operators.

## Notation

To use attribute $a$ from relation $R$ write $R.c$

## Operators

Considering table as if they were sets in the mathematical meaning helps a lot.

Domains of the tables should be the same for the operations to be performed.

* $\cup$ union
* $-$ difference: $R - S$ keeps only the tuples that are in $R$ but not in $S$, no records in $S$ are in the result
* $\cap$ intersection: could also be written as $R \cup S - (R - S) - (S - R)$
* $\sigma$ selection: returns some of the tuples, based on a predicate, <span class="b">one tuple at a time</span> they are checked against the predicate, example $\sigma_{minplayers < 3 \wedge maxplayers \geq 3}(\textsf{boardgame})$
* $\pi$ projection: selects attributes from the tuples, example $\pi_{A,B}{R}$, if the projection makes it so that there would be duplicated tuples these are removed
* $\bowtie$ equi-join: <span class="b">selection over Cartesian product</span>, tuples with same values on shared attributes are put together; 
    * given two tables with the same attributes like $R(A, B, C)$ and $S(A, B, C)$, then $R \bowtie S = R \cap S$
    * given two tables without common attributes like $R(A,B,C)$ and $Z(X,Y,K)$, then $R \bowtie Z = R \times Z$, because we get all attributes and all tuples combinations, from these we remove those that have the same values over the in common attributes, which are none, therefore it is equivalent to the Cartesian product 
* $\bowtie_{\theta}$ theta-join: which is a $\sigma$ applied to a Cartesian product, example $\bowtie_\theta{id=category}$, used to match tuples with others
* $\rho$ rename: for example $\rho_{a\rightarrow b}$ 
* $\div$ division: given $R_{1}$ and $R_{2}$ by doing $R_{1} \div R_{2}$ we get all tuples in $R_{1}$ that combine with $R_{2}$, which is the inverse of Cartesian product

Note on joins: they can be implemented as the Cartesian product with a selection.

## The need for subtraction

There are scenarios where it's tricky to understand what to do, or at least it is for me: say we want to find all board games that are not wargames, these are the tables:

<p class="table-header">Categories</p>

| id  | name    |
| --- | ------- |
| 1   | Wargame |
| 2   | Ancient |

<p class="table-header">GamesCategories</p>

| category | boardgame |
| -------- | --------- |
| 1        | 6         |
| 1        | 4         |
| 2        | 4         |

We can't do $\pi_{GC.boardgame}(\sigma_{C.name\neq Wargame}(GC\bowtie_{GC.category=C.id}C))$ resulting in:


| boardgame | category | name    |
| --------- | -------- | ------- |
| 6         | W        | Wargame |
| 4         | A        | Ancient |

because the $boardgame$ having id 4 has different categories, so we would not show the line where it has $name$ 4, but we would show the like where it has $name$ $Ancient$, which is wrong because this boardgame also is a $Wargame$. 

<span class="b">To verify a negative condition we have to verify all data together, which we can't</span>, because of the [Relational algebra#Fundamental principle](Relational%20algebra#Fundamental%20principle.md).

<aside>Solution when working with negation</aside>
Always obtain those records that fall in the category, and then subtract these from all records: 

$\pi_{id}(B) - \pi_{GC.boardgame}(\sigma_{C.name=Wargame}(GC\bowtie_{GC.category=C.id}C))$

## The need for division

Say you have

<p class="table-header">R</p>

| A   | B   |
| --- | --- |
| 1   | a   |
| 1   | b   |
| 2   | a   |

<p class="table-header">S</p>

| B   |
| --- |
| a   |
| b   |
and you want to find all $R.A$ that have a relation with all $S.B$.
To achieve that we
1) find all correspondence $\pi_{A}(R) \times \pi_{B}(S)$
2) remove all correspondence in $R$ $\pi_{A}(R) \times \pi_{B}(S) - R$, so we get elements from $A$ that are missing a correspondence from elements in $B$
3) remove from all possible elements what we got at 1) $\pi_{A}(R) - \pi_{A}(\pi_{A}(R) \times \pi_{B}(S) - R)$

This operation is called division $\div$.

### More info

[Great explanation](https://www2.cs.arizona.edu/~mccann/research/divpresentation.pdf).

Given relation $R$ and $S$, $R \div S$ identifies attribute values in $R$ that can be paired with all of the values in $S$. It is the inverse of Cartesian product.

![relational-algebra-cartesian-product.png](relational-algebra-cartesian-product.png) 
![relational-algebra-division.png](relational-algebra-division.png)

![relational-algebra-in-short.png](relational-algebra-in-short.png)

If $U = R \times S$ then $U \div S = R$ e $U \div R = S$.

## Talking about "all"

It is useful when you need to "find all X that Y" to think about it in a different way, so try to "find all X that do not Y" and then remove from X what you just found (hope this makes sense).

In other scenarios it might be more difficult to "find all X that are Y in all Z", so you could:
* create a new table called $All$, using Cartesian products, that contains all possible solutions available in the data
* from $All$ we remove those records that match out condition, so we get $Opposite$, which are those that do not match the condition
* finally from the existing records we remove $Opposite$

and so we arrive to the result. <span class="b">Which is the same of doing a division</span>.

We are doing a double negation, so to make an example with BoardGame: "we take games such that it does not exist a game such that it is not in first position".

## Notes

Cartesian product between something and the empty set is the empty set $R \times \emptyset = \emptyset$.

Sometimes it might be useful to invert the sentence and ask ourselves the negated version of it.

## Exercises

### Boardgame

Following examples use this schema:

![Pasted image 20240201195400.png](Pasted%20image%2020240201195400.png)

<aside>writing a query</aside>
How to approach writing a query:

* imagine the data I need, what characteristics does it have? 
* which tables do we need?
* which attributes do we need? selections
* when dealing with negation first find the query without the negation then subtract

1. name of those games that have an average score of 0.7
$$\pi_{gvalue}(\sigma_{Ratings.average\gt0.7}(Gamename\bowtie Ratings))$$
There is no need to specify the attributes since they're the same

2. games that are wargames
$$\pi_{GameCategory.boardgame}(\sigma_{cname="Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$
Since we don't have attributes with the same name now we need the condition in the join

3. games that are not wargames
$$\pi_{GameCategory.boardgame}(\sigma_{cname\neq"Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$
<aside>Note on negating conditions</aside>
That query is wrong though, because there could be games that are both "Wargame" and "Ancient" for example.
Since we query line by line we don't have visibility on the relationship as a whole.
This is different from looking a game that's a "Wargame".
The idea would be to take from all boardgames the ones that are "Wargame":

$$\pi_{id}(boardgame) - \pi_{GameCategory.boardgame}(\sigma_{cname\neq"Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$

4. Given the following we want to find all values of `R.A` that have a correspondance with all values `S.B`. Could be useful when you want to find all students that attended all courses for example.
![Pasted image 20240201221133.png](Pasted%20image%2020240201221133.png)
$$\pi_A(R) \times \pi_{B}(S) - R$$
With the first part $\pi_A(R) \times \pi_{B}(S)$ we get all possible combinations, we remove `R` so we get the online one that's missing.
So we arrive to $\pi_{A}(R) - \pi_{A}(\pi_A(R) \times \pi_{B}(S) - R)$
This last operation is called division.

5. All boardgames of type "Wargame" playable by at least three players
$\pi_{B.id}(\sigma_{B.minp\geq 3 \wedge C.name="Wargame"}((Boardgame\bowtie_{B.id=GC.gamecategory}GameCategory) \bowtie_{GC.category = C.id}))$ 
or an optimized version
$(\pi_{B.id}(\sigma_{B.minp \geq 3}(B)) \bowtie_{B.id = G.boardgame} G) \bowtie_{G.cateogry=C.id} \pi_{C.id}(\sigma_{C.name="Wargame"}C)$

6. Find all producers that had both designer and artist role in the same game
$\pi_{C_{1}.production}(\sigma_{C_{1}.ctype=designerC_{1}}$
$\bowtie_{C_{1}.boardgame=C_{2}.boardgame \wedge C_{1}.poduction=C_{2}.production}$
$\sigma_{C2.ctype=artist}C_{2})$
or also
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=designer}C_{1})$
$\bowtie$
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=artist}C_{1})$
or also
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=designer}C_{1})$
$\cap$
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=artist}C_{1})$

7. Find all games that are first in all their rankings
Looking at the `Ranking` table above we see we might have this situation.
So doing $\sigma_{position=1}Ranking$ produces a wrong result since boardgame with id `1` has position `2` for rank `2` so it should not appear in a query that is listing all games that are first in all their rankings, because it's clearly second here. 
So let's find all games that do not have position 1 and then subtract this from all rankings.
$\pi_{boardgame}R - \pi_{boardgame}(\sigma_{position\neq 1}Ranking)$

8. Find all games that are first in all rankings
All games that are not first in some ranking
$NotFirst = \pi_{Boardgame.id}(\pi_{id}Boardgame \times \pi_{id}Ranking \times \pi_{pos}(\sigma_{pos=1}Ranking)$
$-$
$\pi_{rank,boardgame, position}Ranking(\sigma_{pos=1}Ranking))$
and then
$\pi_{id}Boardgame - NotFirst$
or using the $\div$ operator
$\pi_{boardgame,rank,pos}Ranking \div \pi_{rank,position}(\sigma_{pos=1}Ranking)$

9. Find users that have commented all wargame boardgames
$\pi_{bgguser,boardgame}(Comment)$
$\div$
$\pi_{boardgame}(\sigma_{cnmame="Wargame"}Category \bowtie_{Category.id=GameCategory.category}GameCategory)$
or also
$\pi_{bgguser}Category - \pi_{bgguser}(\pi_{bgguser}C \times \pi_{boardgame}(\sigma_{cname=Wargame}Category \bowtie_{Category.id=GameCategory.category}GameCategory)$
$-$
$\pi_{bgguser.boardgame}((Category \bowtie_{Category.boardgame=GameCategory.boardgame}GameCategory) \bowtie \sigma_{cname=Wargame}(Category))$

10. Find all games that are not sold at less than a 100E
$\pi_{boardgame}Listings - \pi_{boardgame}(\sigma_{currency=EU \wedge price\lt 100}Listings)$

### Cities

![relational-algebra-politicians.png](relational-algebra-politicians.png)

1. Find the names of politicians that are in charge in cities of nations where they were not born into
$\sigma_{C.country \neq P.birth\_place}(Politician \bowtie_{P.id=C.head}City)$
or
$Politician \bowtie_{P.id=C.head \wedge C.country \neq P.birth\_place}City$

2. Find cities that have a greater population than their nearby cities
$NEARBY\_CITIES = \pi_{id,population}City_{1} \bowtie_{C_{1}.id=CB.city_a} City\_Borders \bowtie_{C_{2}.id=CB.city_b} City_{2}$
$NEARBY\_CITIES\_NOT\_GREATER = \sigma_{C_{2}.population \gt C_{1}.population}$
So these are the cities for which there exists another nearby city with a greater population, which is what I DO NOT want, so now I just need to subtract
$\pi_{id}Cities - \pi_{C_{1}.id}(NEARBY\_CITIES\_NOT\_GREATER)$

3. Find nations in which there are no cities ruled by politicians born before 1960
I will find countries where there is a politician born before 1960 and then I subtract from the list of countries.
$COUNTRIES = \pi_{country}(\sigma_{birth\_date<01.01.1960}Politician \bowtie_{P.id=C.head} City)$
$\pi_{C.id}Country - COUNTRIES$

4. Find politicians that are head in more than one city
$\pi_{C_{1}.head}(City_1 \bowtie_{City_1.id \neq City_2.id \wedge City_1.head = City_2.head} City_2)$

