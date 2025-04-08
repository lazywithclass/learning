---
cssclasses: 
tags:
  - sql
  - postgres
  - postgresql
  - query
  - databases
  - italian
---
Structured Query Language

Linguaggio dichiarativo.

DDL: comandi per creare strutture per l'ossatura della base di dati (`CREATE TABLE`, `CREATE VIEW`, ...)
DML: istruzioni (`INSERT`, `DELETE`, `UPDATE`, ...)
DQL: `SELECT`
DCL: comandi sul controllo

## Funzioni / operatori / clausole

* `lower` trasforma la stringa in lowercase
* `like` 
* `ilike` insensitive `like`
* `trim` rimuove gli spazi prima e dopo
* `ltrim` spazi a sinistra
* `rtrim` spazi a destra
* `between` ad esempio `... vote BETWEEN 1 and 10;`
* `in` ad esempio `... lower(genre) in ('drama', 'thriller', 'crime')`
* `distinct` 
	* ad esempio `SELECT DISTINCT movie FROM imdb.genre` (elimina i duplicati, rendendo il risultato "come fosse in algebra relazionale")
	* `SELECT DISTINCT movie, genre FROM imdb.genre` perché `DISTINCT` lavora sull'intera clausola `SELECT`
* `order by` ordina ciò che restituiamo, e' l'ultima cosa che viene fatta; si può specificare `DESC` per più attributi in base alle necessita'
* `extract` permette di prendere parti di una data come ad esempio `WHERE extract(YEAR FROM birth_date) = '1971'`
* `as` e' l'equivalente di $\rho$ nell'algebra relazionale
* `::` e' l'operatore di cast `SELECT extract(year) from birth_date)::char(4) from imdb.person`
* `is null` unico modo per confrontare con `NULL`  
* `ALL` da true solo se il predicato e' verificato per tutti i valori restituiti dalla sotto query
* `ANY` da true se il predicato e' verificato per almeno un valore restituito dalla sotto query
* `min/max` (agg)
* `avg` (agg)
* `sum` (agg)
* `count` (agg) cardinalità di una relazione
	* `COUNT(*)` conta le righe
	* `COUNT(attributo)` conta le occorrenze in cui l'attributo non e' `NULL`

(agg) sono operatori aggregati, ignorano il `NULL`; vengono eseguiti alla fine

La precedenza degli operatori logici viene valutata da sinistra verso destra, quindi
```sql
SELECT *
FROM imdb.movie
WHERE year '2010' OR length >= 60 AND length <= 120;
```

Prima valuta `year '2010' OR length >= 60` e poi il risultato di questa in `AND`.

`%` segnaposto per una stringa di qualsiasi lunghezza
`_` segnaposto per una stringa di lunghezza 1

## Prodotto cartesiano

```sql
SELECT *
FROM imdb.movie, imdb.produced
```

Cardinalità totale e' uguale a cardinalità movie (1033) per cardinalità produced (1332).

## Join

Sono una selezione sul prodotto cartesiano. 

```sql
SELECT *
FROM imdb.movie m, imdb.produced p
WHERE m.id = p.movie
```

`movie.id` chiave primaria, `produced.movie` chiave esterna.
Cardinalità e' uguale a cardinalità di produced.

Sintassi alternativa

```sql
SELECT m.id, m.official_title
FROM imdb.movie m 
INNER join imdb.produced p ON m.id = p.movie
WHERE country = 'USA'
```

`a JOIN b ON c` restituisce i record di `a` e `b` nel prodotto cartesiano che soddisfano `c`.

Eventuali `WHERE` vengono applicate dopo la `JOIN`!

## Join esterno

Aggiunge al join eventuali record che non hanno alcuna corrispondenza nella tabella di destra (nel caso di `LEFT JOIN`).

La cardinalità quindi include anche i record senza corrispondenza. 

`FULL JOIN` combina `LEFT` e `RIGHT`.
## Viste

Oggetti derivati formati a partire dai dati nelle tabelle, per offrire delle proiezioni dei dati tipicamente appartenenti a tabelle diverse.


```sql
CREATE VIEW movie_person as (
	SELECT *
	FROM movie 
	INNER JOIN crew on movie.id crew.movie
	INNER JOIN person on person.id = crew.person
)
```

A questo punto trovare le persone di inception diventa quanto segue.
Prima viene eseguita `movie_person` e poi viene eseguita la seconda query (nessuna duplicazione dei dati).

```sql
SELECT *
FROM movie_person
WHERE official_title ilike 'inception' AND p_role 'actor'
```

## Explain

Controllare i costi di una query, mostra l'albero di esecuzione della query. 
Si parte a leggere dalle foglie.

```sql
EXPLAIN ANALYZE ...
```

## Query correlata

Molto inefficiente
TODO espandere


---
## Esercizi

![IMDB Schema](attachments/imdb-schema.png)

* Selezionare il titolo delle pellicole del 2010
```sql
SELECT official_title
FROM imdb.movie 
WHERE year = '2010';
```

* Cercare pellicole che hanno "murder" nel titolo
```sql
SELECT *
FROM imdb.movie
WHERE official_title LIKE '%murder%'
```

* Trovare le pellicole prodotte in due paesi diversi
```sql
SELECT *
FROM imdb.produced p1, imdg.produced p2
WHERE p1.movie = p2.movie AND p1.country <> p2.country
```

Pero' in questo caso ottengo duplicati, quindi dovrei rimuoverli, posso risolvere usando il `<`

```sql
SELECT p1.movie, p1.country as country1, p2.country as country2
FROM imdb.produced p1, imdg.produced p2
WHERE p1.movie = p2.movie AND p1.country < p2.country
```

* Persone decedute in un paese diverso da quello di nascita

```sql
SELECT *
FROM location l1, location l2
WHERE l1.person = l2.person AND
	l1.d_role <> l2.d_role AND l1.country <> l2.country
```

Ma se la lascio cosi ha duplicati, per rimuoverli uso

```sql
SELECT *
FROM location l1, location l2
WHERE l1.person = l2.person AND
	l1.d_role = 'B' AND l2.d_role = 'D' AND
	l1.country <> l2.country
```

* Trovare le pellicole che non hanno materiali

```sql
SELECT movie.id
FROM movie

EXCEPT

SELECT DISTINCT material.movie
FROM material
```

* Trovare le pellicole che sono prodotte in ITA e USA

La seguente e' sbagliata! Scorre la tabella produced cercando un record che contemporaneamente abbia country `'ITA'` e `'USA'`

```sql
SELECT *
FROM produced 
WHERE country = 'ITA' AND country = 'USA'
```

Viene semplicissimo con l'intersezione occhio a non usare `*` perché in un caso filtriamo country con `ITA` e nell'altro con `USA` quindi saranno sempre diversi, quindi mai intersecabili

```sql
SELECT movie FROM produced WHERE country = 'ITA'
INTERSECT
SELECT movie FROM produced WHERE country = 'USA'
```

* Trovare i titoli delle pellicole prodotte in ITA e USA

```sql
SELECT id, official_title
FROM produced JOIN movie ON movie = id 
WHERE country = 'ITA'
INTERSECT
SELECT id, official_title
FROM produced JOIN movie ON movie = id 
WHERE country = 'USA'
```

Metto anche `id` nella `SELECT` per avere una intersezione solo quando anche l'`id` (che e' chiave) e' uguale, altrimenti potrei avere duplicati.

Posso farlo anche con una CTE <label class="sidenote-toggle sidenote-number"></label>.
<span class="sidenote">Common Table Expressions</span>

```sql
WITH mp AS (
	SELECT * FROM produced INNER JOIN movie ON movie = id
)
SELECT id, official_title FROM mp WHERE country = 'ITA'
INTERSECT
SELECT id, official_title FROM mp WHERE country = 'USA'
```

Il beneficio e' creare un oggetto in memoria, temporaneo.
Utile quando ho bisogno di diversi componenti.

Oppure ancora

```sql
SELECT id, official_title FROM movie WHERE  id IN (
	SELECT movie FROM produced WHERE country = 'ITA'
	INTERSECT
	SELECT movie FROM produced WHERE country = 'USA'
)
```

Oppure ancora con self join (che e' anche un razzo)

```sql
SELECT usa.movie
FROM produced usa 
JOIN produced ita ON usa.movie = ita.movie
JOIN movie ON usa.movie = id
WHERE usa.country = 'USA' AND ita.country = 'ITA'
```

* Trovare titolo dei film con durata superiore alla durata di inception

La seguente non va bene senza `ANY` perché potrei avere più risultati dalla inner query

```sql
SELECT id, official_title, length
FROM imdb.movie
WHERE length > ANY (
	SELECT LENGTH
	FROM imdb.movie
	WHERE official_title = 'Inception'
)
```

O anche

```sql
SELECT DISTINCT m1.id, m1.official_title
FROM movie m1 JOIN movie m2
ON m1.length > m2.length
WHERE m2.official_title = 'Inception'
```

TODO e' possibile usare SELF JOIN per emulare il comportamento della ALL? 

* Trovare le pellicole prodotte solo in Italia

```sql
SELECT p.movie
FROM produced p
WHERE p.country = 'ITA' AND movie NOT IN (
	SELECT movie
	FROM produced
	WHERE country <> 'ITA'
)
```

Oppure con la sottrazione

```sql
SELECT movie FROM produced WHERE country = 'ITA'
EXCEPT 
SELECT movie FROM produced WHERE country <> 'ITA'
```

Oppure con join esterno TODO

```sql
SELECT 
```

* Trovare il titolo di tutti i film con relativi generi

La seguente non troverebbe i film che non hanno genere

```sql
SELECT id, official_title, genre
FROM movie 
JOIN genre ON movie.id = genre.movie
```

quindi

```sql
SELECT id, official_title, genre
FROM movie
LEFT JOIN genre ON movie.id = genre.movie
```

* Per ogni persona mostrare il nome e il country dove e' deceduto, incluse le persone per le quali non abbiamo un country di decesso

```sql
SELECT id, given_name, country
FROM person 
LEFT JOIN location ON person.id = location.person
WHERE d_role = 'D'
```

La `WHERE` viene valutata sull'effetto del `JOIN`, quindi lo annulla, perché per i record spuri `d_role` sarebbe `NULL`, quindi la query non e' corretta perché non aggiunge i `NULL`.

Possibile soluzione in cui filtriamo prima 

```sql
WITH deaths AS (
	SELECT *
	FROM location l
	WHERE d_role = 'D'
)
SELECT id, given_name, country
FROM person 
LEFT JOIN deaths ON person.id = deaths.person
```

Oppure imponiamo il filtro nel momento del join

```sql
SELECT id, given_name, country
FROM person 
LEFT JOIN location ON person.id = location.person AND d_role = 'D'
```

* Trovare coppie di pellicole che non hanno generi in comune

"Da tutte le possibili combinazioni, togliamo le pellicole che hanno un genere in comune"

```sql
SELECT DISTINCT g1.movie, g2.movie
FROM genre g1 JOIN genre g2 ON g1.movie > g2.movie
except
SELECT DISTINCT g1.movie, g2.movie
FROM genre g1 JOIN genre g2 ON g1.movie > g2.movie
WHERE g1.genre = g2.genre
```

oppure (notare l'uso di `c` nella [Query correlata](SQL.md#Query%20correlata))

```sql
WITH couples as (
	SELECT DISTINCT g1.movie AS movie1, g2.movie AS movie2
	FROM genre g1 JOIN genre g2 ON g1.movie > g2.movie
)
SELECT * 
FROM couples c
WHERE NOT EXISTS (
	SELECT * ;; irrilevante cosa abbiamo qui
	FROM genre g1, genre g2
	WHERE g1.movie = c.movie1 AND g1.movie = c.movie2 AND g1.genre = g2.genre
)
```

* Trovare i film che non sono stati distribuiti nei paesi nei quali sono stati prodotti

Tabelle interessate: produced e released

Un movie `m` viene inserito nel risultato se non esiste un record della tabella produced `p` per il quale esiste un record di release relativo allo stesso movie e paese di `p` 

```sql
SELECT id, official_title
FROM movie m
WHERE NOT EXISTS (
	SELECT *
	FROM produced p
	WHERE p.movie = m.id AND EXISTS (
		SELECT *
		FROM released r
		WHERE r.movie = m.id AND p.country = r.country
	)
)
```

Utile quando devo contemporaneamente far valere più vincoli. Con gli operatori insiemistici non me la cavo perché devo controllare le condizioni su ogni singolo record. TODO ESPANDERE Con esempio e spiegazione

Oppure

```sql
SELECT id, official_title
FROM movie m
WHERE NOT EXISTS (
	SELECT *
	FROM produced p JOIN released r ON p.movie = r.movie AND 
		p.country = r.country
	WHERE p.movie = m.id
)
```

* Trovare il film di durata maggiore

```sql
SELECT max(length)
FROM movie
```

* Trovare il film di durata maggiore e restituire il titolo

La seguente va in errore perché nell'aggregare perdono il riferimento alla tupla che contiene quel valore, inoltre potrei avere più di un record con quel valore, quindi devo aggregare tutto ciò che voglio selezionare

```sql
SELECT id, official_title, max(length) FROM movie
```

Un modo per farlo e'

```sql
SELECT * FROM movie WHERE length = (
	SELECT MAX(length) as durata_massima FROM movie
)
```

Oppure anche

```sql
WITH mmax AS (
	SELECT MAX(length) as durata_massima FROM movie
)
SELECT m.* 
FROM movie JOIN mmax ON m.length = mmax.durata_massima
```

* Trovare il numero di pellicole per le quali e' noto l'anno di produzione

Non serve specificare `WHERE year IS NOT NULL` (vedi descrizione `COUNT`)

```sql
SELECT count(year) FROM movie
```

Invece cosi serve

```sql
SELECT COUNT(*) FROM MOVIE WHERE year IS NOT NULL
```

* Trovare il numero di titoli diversi delle pellicole

```sql
SELECT COUNT(DISTINCT official_title) FROM movie
```













---

old file




## Notes

### Subtraction

In [Relational algebra](Relational%20algebra.md) we can do the subtraction, in SQL this could be achieved using
* `NOT IN`
```sql
SELECT C.iso3 
FROM imdb.country AS C
WHERE C.iso3 NOT IN (
SELECT DISTINCT P.country FROM imdb.produced as P)
)
```
* `EXCEPT`
```sql
SELECT C.iso3 FROM imdb.country AS C
EXCEPT
SELECT DISTINCT P.country FROM imdb.produced as P
```

A comparison between relational algebra's operators and sql's:
* `UNION` $\rightarrow$ $\cup$
* `INTERSECT` $\rightarrow$ $\cap$
* `EXCEPT` $\rightarrow$ $-$

### Finding the `Max`

Find the movie with the greatest score

Using `ANY`
```sql
SELECT *
FROM imdb.ratings AS R2
WHERE R2.score >= ANY (SELECT R.score FROM imdb.ratings AS R)
```

or using `MAX`
```sql
SELECT *
FROM imdb.ratings AS R2
WHERE R2.score >= (SELECT MAX(R.score) FROM imdb.ratings AS R)
```

Do not do this, as it just returns the first, even though there might be more than 1 with the same value

```sql
SELECT *
FROM imdb.ratings AS R
ORDER BY R.score DESC
LIMIT 1
```

### `SELECT` and aggregation operators

`SELECT`s work on single rows, aggregation operators work on a set of rows.

## Practice

These are mainly exercises along with some explanations in preparation for a database exam I have coming along.

### Department highest salary

```
Employee
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+

Department
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
```

```postgresql
select d.Name as Department, e.Name as Employee, e.Salary
from Employee as e
join Department as d on e.DepartmentId = d.Id
join (
	select max(Salary) as Salary, DepartmentId
	from Employee
	group by DepartmentId
) as mx
on e.Salary = mx.Salary and e.DepartmentId = mx.DepartmentId
```


### Ranking alongside records

```
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| score       | decimal |
+-------------+---------+
```

```postgresql
SELECT S1.score,
  (
    SELECT COUNT(DISTINCT S2.score)
    FROM Scores S2
    WHERE S2.score >= S1.score
  ) AS rank
FROM Scores S1
ORDER BY S1.score DESC;
```

### Consecutive Available Seats

```
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
```
`seat_id` is an auto-increment column for this table.
Each row of this table indicates whether the ith seat is free or not. 1 means free while 0 means occupied.

```postgresql
SELECT seat_id
FROM Cinema AS c1
WHERE EXISTS (
    SELECT 1
    FROM Cinema AS c2
    WHERE (c1.seat_id + 1 = c2.seat_id OR c1.seat_id = c2.seat_id + 1)
        AND c1.free = 1
        AND c2.free = 1
)
ORDER BY seat_id;
```