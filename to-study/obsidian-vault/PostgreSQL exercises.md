---
cssclasses:
  - cornell-note
tags:
  - sql
  - postgres
  - postgresql
  - query
  - databases
---

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