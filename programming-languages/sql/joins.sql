(defun table-structure (table-name)
  (shell-command-to-string
    (concat sql-postgres-program
      " -h /home/nixos/workspace/learning/sql/data"
      " -d dvdrental"
      " -c \"SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '"
      table-name
      "'\""
      " -U postgres ")))

(table-structure "category")
(table-structure "rental")
(table-structure "product")


-- Thanks to ChatGPT for providing the exercises
-- and to https://www.postgresqltutorial.com/ for the data


-- WHERE  works on rows
-- HAVING works on groups

SET search_path TO dvdrental, public;

-- Retrieve the top 3 most recent orders for each customer.
-- Include the customer's first name, last name, and the order date.
SELECT c.first_name, c.last_name, r1.rental_date
FROM customer c
JOIN rental r1 ON c.customer_id = r1.customer_id
JOIN rental r2 ON c.customer_id = r2.customer_id AND r1.rental_date <= r2.rental_date
GROUP BY c.customer_id, r1.rental_date, r1.rental_id
HAVING COUNT(*) <= 3
ORDER BY c.customer_id, r1.rental_date DESC;


-- Find the customers who have placed orders for all available products.
-- Display the customer's first name, last name, and the count of products they
-- have ordered.
SELECT r.customer_id
FROM rental r
JOIN inventory i ON r.inventory_id = i.inventory_id
GROUP BY r.customer_id
HAVING COUNT(DISTINCT i.film_id) = (SELECT COUNT(film_id) FROM film);


-- Find the customer that rented the most
SELECT COUNT(r.customer_id) AS rents, r.customer_id
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
GROUP BY r.customer_id
ORDER BY rents DESC
LIMIT 1;


-- Find the customer that rented the most


-- Find the customers that rented most than the average


-- Retrieve the first name, last name, and email of customers who have placed
-- more orders than the average number of orders across al cities
-- (national average)
SELECT cus.first_name, cus.last_name, cus.email
FROM customer cus
JOIN address a ON cus.address_id = a.address_id
JOIN rental r ON cus.customer_id = r.customer_id
GROUP BY a.city_id, cus.customer_id
HAVING COUNT(cus.customer_id) > (
       SELECT SUM(city_order_count) / COUNT(*) AS average_orders
       FROM (
            SELECT a.city_id, COUNT(*) AS city_order_count
            FROM customer cus
            JOIN address a ON cus.address_id = a.address_id
            JOIN rental r ON cus.customer_id = r.customer_id
            GROUP BY a.city_id
       ) AS orders_in_cities);


-- What are the top rented genres?
SELECT c.name, COUNT(r.rental_id) as tot_rentals
FROM rental r
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON f.film_id = i.film_id
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category c ON c.category_id = fc.category_id
GROUP BY c.category_id, c.name
ORDER BY tot_rentals DESC;


-- What is the movie that generated the most payments?
SELECT f.title, SUM(p.amount) as tot_amount
FROM film f
JOIN inventory i ON i.film_id = f.film_id
JOIN rental r ON r.inventory_id = i.inventory_id
JOIN payment p ON p.rental_id = r.rental_id
GROUP BY f.film_id, f.title
ORDER BY tot_amount DESC
LIMIT 10;


-- Which are the users that rented the most genres?
WITH T1 AS (SELECT c.name,
        COUNT(r.rental_id) as tot_rentals,
        cus.customer_id
     FROM customer cus
     JOIN rental r ON r.customer_id = cus.customer_id
     JOIN inventory i ON i.inventory_id = r.inventory_id
     JOIN film f ON f.film_id = i.film_id
     JOIN film_category fc ON fc.film_id = f.film_id
     JOIN category c ON c.category_id = fc.category_id
     GROUP BY cus.customer_id, c.category_id
)
SELECT t1.customer_id, COUNT(*) rented_count
FROM T1
GROUP BY customer_id
ORDER BY rented_count DESC
LIMIT 10;


-- Which is the average price for each genre?
SELECT c.name, AVG(p.amount)
FROM payment p
JOIN rental r ON r.rental_id = p.rental_id
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON f.film_id = i.film_id
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category c ON c.category_id = fc.category_id
GROUP BY c.category_id, c.name


-- Find all movies that have a longer rental duration than a certain
-- movie passed to the query
SELECT f1.film_id, f1.title, f1.rental_duration
FROM film f1
JOIN film f2 ON f1.rental_duration > f2.rental_duration AND f2.film_id = 425;

-- (follows a query just to get a random film and see its data)
SELECT film_id, title, rental_duration
FROM film
WHERE film_id = 425;


-- Find the films that have more actors than the average of the actors
-- for that film category
WITH total_actors AS (
    SELECT film_id, COUNT(actor_id) AS actor_count
    FROM film_actor
    GROUP BY film_id
),
average_actors AS (
    SELECT fc.category_id, AVG(actor_count) AS avg_actors_per_category
    FROM film_category fc
    JOIN total_actors ta ON fc.film_id = ta.film_id
    GROUP BY fc.category_id
)
SELECT f.film_id, f.title, COUNT(DISTINCT fa.actor_id) AS actors_count
FROM film f
JOIN film_actor fa ON f.film_id = fa.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
JOIN average_actors ON c.category_id = average_actors.category_id
GROUP BY f.film_id, f.title, average_actors.avg_actors_per_category
HAVING COUNT(DISTINCT fa.actor_id) > average_actors.avg_actors_per_category;


-- Find the customers who have rented all the films available
-- in the "Action" category
WITH rented_action_films AS (
    SELECT c.customer_id, COUNT(*) AS rented_action_films_for_customer
    FROM customer c
    JOIN rental r ON r.customer_id = c.customer_id
    JOIN inventory i ON i.inventory_id = r.inventory_id
    JOIN film f ON f.film_id = i.film_id
    JOIN film_category fc ON fc.film_id = f.film_id
    JOIN category cat ON cat.category_id = fc.category_id
    WHERE cat.name = 'Action'
    GROUP BY c.customer_id
)
SELECT *
FROM rented_action_films raf
WHERE rented_action_films_for_customer = (
    SELECT COUNT(*) AS action_films_count
    FROM film f
    JOIN film_category fc ON fc.film_id = f.film_id
    JOIN category c ON c.category_id = fc.category_id
    WHERE c.name = 'Action'
    GROUP BY fc.category_id
);


-- Retrieve the film titles that have been rented the most
SELECT f.title, COUNT(*) AS tot_rentals
FROM rental r
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON f.film_id = i.film_id
GROUP BY i.film_id, f.title
ORDER BY tot_rentals DESC
LIMIT 10;


-- Calculate the average length of films rented by customers from each city.
-- Display the city name and the average film length.
SELECT c.city, AVG(f.length) AS average_film_length
FROM rental r
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON f.film_id = i.film_id
JOIN staff s ON s.staff_id = r.staff_id
JOIN store sto ON sto.store_id = s.store_id
JOIN address a ON a.address_id = sto.address_id
JOIN city c ON c.city_id = a.city_id
GROUP BY c.city_id, c.city;


-- Write a query to find the top 5 customers who have made the highest
-- total payments.
SELECT c.customer_id, c.first_name, c.last_name, SUM(p.amount) tot_paid
FROM customer c
JOIN rental r ON r.customer_id = c.customer_id
JOIN payment p ON p.rental_id = r.rental_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY tot_paid DESC
LIMIT 5;


-- Find the films that have not been rented by any customer.
-- Display the film title and the number of times it has
-- been rented (should be zero).
SELECT f.title
FROM film f
JOIN inventory i ON i.film_id = f.film_id
LEFT JOIN rental r ON r.inventory_id = i.inventory_id
GROUP BY f.title
HAVING COUNT(r.rental_id) = 0;


-- Retrieve the top 10 customers who have rented the highest number of unique
-- films across all categories. Display their customer ID, first name, last
-- name, and the count of unique films they have rented.
SELECT c.customer_id, COUNT(DISTINCT f.film_id)
FROM customer c
JOIN rental r ON r.customer_id = c.customer_id
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON f.film_id = i.film_id
GROUP BY c.customer_id
LIMIT 10;


-- Calculate the total revenue generated from each film category.
-- Display the category name and the total revenue (sum of all payments
-- for rentals from that category).
SELECT c.name, SUM(p.amount)
FROM category c
JOIN film_category fc ON fc.category_id = c.category_id
JOIN film f ON f.film_id = fc.film_id
JOIN inventory i ON i.film_id = f.film_id
JOIN rental r ON r.inventory_id = i.inventory_id
JOIN payment p ON p.rental_id = r.rental_id
GROUP BY fc.category_id, c.name;
