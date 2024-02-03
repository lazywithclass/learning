---
cssclasses:
  - cornell-note
tags:
  - sql
  - postgres
  - postgresql
  - subquery
---






# Practice

These are mainly exercises along with some explanations in preparation for a database exam I have coming along.

### When should I use an inner query?

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