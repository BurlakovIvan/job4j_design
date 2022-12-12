/* 1
*/
CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers(first_name, last_name, age, country)
VALUES ('ivan', 'ivanov', 25, 'russia'),
       ('alexander', 'petrov', 20, 'russia'),
       ('petr', 'sidorov', 36, 'russia'),
       ('dmitriy', 'sidorov', 24, 'russia'),
       ('evgeniy', 'sergeev', 20, 'russia'),
       ('oleg', 'ivanov', 21, 'russia'),
       ('mikhail', 'perov', 45, 'russia'),
       ('sergey', 'petrov', 33, 'russia'),
       ('pavel', 'malykh', 22, 'russia'),
       ('valdimir', 'sidorov', 21, 'russia');

SELECT * FROM customers c
WHERE c.age = (SELECT MIN(age) FROM customers);

/* 2
*/
CREATE TABLE IF NOT EXISTS orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders(amount, customer_id)
VALUES (2, 1), (5, 3), (4, 6), (5, 4), (7, 2), (2, 8);

SELECT * FROM customers c
WHERE c.id NOT IN (SELECT customer_id FROM orders);