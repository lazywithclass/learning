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

## Operators

Considering table as if they were sets in the mathematical meaning helps a lot.

Domains of the tables should be the same for the operations to be performed.

* $\cup$ union
* $\cap$ difference : `R - S` keeps only the tuples that are in `R` but not in `S`, no records in `S` are in the result
* $-$ intersection : could also be written as `R ∪ S - (R - S) - ( S - R)`
* $\sigma$ selection : returns some of the tuples, based on a predicate, one tuple at a time they are checked against the predicate, example $\sigma_{minplayers < 3 \wedge maxplayers \geq 3}(\textsf{boardgame})$
* $\pi$ projection : selects attributes from the tuples, example $\pi_{A,B}{R}$, if the projection makes it so that there would be duplicated tuples these are removed
* $\bowtie_{\theta}$ theta-join : which is a $\sigma$ applied to a Cartesian product, example $\bowtie_\theta{id=category}$, used to match tuples with others
* $\bowtie$ equi-join : get together tuples with same values on shared attributes
* $\rho$ rename : for example $\rho_{a\rightarrow b}$ 
* $\div$ division : given $R_{1}$ and $R_{2}$ by doing $R_{1} \div R_{2}$ we get all tuples in $R_{1}$ that combine with $R_{2}$, which is the inverse of Cartesian product 

### On division

[Great explanation](https://www2.cs.arizona.edu/~mccann/research/divpresentation.pdf).

Given relation $R$ and $S$, $R \div S$ identifies attribute values in $R$ that can be paired with all of the values in $S$. It is the inverse of Cartesian product.

![[relational-algebra-cartesian-product.png|500]] ![[relational-algebra-division.png|545]]

![[relational-algebra-in-short.png|500]]

If $U = R \times S$ then $U \div S = R$ e $U \div R = S$.

### Examples

Following examples use this schema:

![[Pasted image 20240201195400.png]]

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
![[Pasted image 20240201221133.png|500]]
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

### Other examples on different tables
![[relational-algebra-politicians.png]]

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

## The need for subtraction

There are scenarios where it's tricky to understand what to do, or at least it is for me: say we want to find all continents where there are no cities where women are in charge.

To me the easiest approach would be to $Country \bowtie_{Country.id=City.country} (City \bowtie_{C.head=P.id} (\sigma_{gender=male}Politician))$, but this is wrong because I am returning all $Country$ that have cities that have a male politician at their head, but that does not mean that all cities in that country have males politicians at their head.

To return a valid result I would have to take into account all continents ($C$ below) and then remove from those all those continents that have at least a women as head in one of their cities ($CWFH$), missing $\pi$s for clarity:

$CFH = \sigma_{P.gender=female}Politician \bowtie_{P.id=C.head} City \bowtie Part\_Of$
$\pi_{id}Continent - \pi_{continent}CFH$

## Notes

Cartesian product between something and the empty set is the empty set $R \times \emptyset = \emptyset$.

<aside>query line by line</aside>
Since we query line by line we don't have visibility on the relationship as a whole. This is important to remember.

Sometimes it might be useful to invert the sentence and ask ourselves the negated version of it.


