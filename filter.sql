/*
product(id, name, type_id, expired_date, price)
type(id, name)
*/
CREATE TABLE type(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250));

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250),
    type_id INT REFERENCES type(id),
    expired_date DATE,
    price FLOAT);

INSERT INTO type(name)
VALUES ('МОЛОКО'), ('СЫР'), ('ХЛЕБ'), ('СМЕТАНА'), ('КВАС'), ('СОСИСКИ');

INSERT INTO product(name, type_id, expired_date, price)
VALUES ('МОЛОКО ХОРОШОЕ', 1, '2022-07-18', 500),
       ('МОЛОКО ПЛОХОЕ', 1, '2022-01-10', 1500),
       ('Сыр плавленный', 2, '2022-07-21', 800),
       ('Сыр обычный', 2, '2022-07-25', 100),
       ('Хлеб нарезной', 3, '2022-07-14', 120),
       ('Хлеб бородинский', 3, '2022-07-12', 200),
       ('Сметана', 4, '2022-08-28', 450),
       ('Квас', 5, '2022-07-13', 650),
       ('Квас холодный', 5, '2022-07-14', 700),
       ('Сосиски с мясом', 6, '2022-06-10', 1500),
       ('Сосиски соевые', 6, '2024-01-01', 300),
       ('Сыр как сыр', 2, '2022-12-19', 1000);
/*1*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
WHERE type.name = 'СЫР';

/*2*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
WHERE product.name LIKE '%мороженое%';

/*3*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
WHERE product.expired_date < NOW();

/*4*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
WHERE product.price = (SELECT MAX(price) FROM product);

/*5*/
SELECT type.name AS "Тип", COUNT(product.name) AS "Количество"
FROM product JOIN type ON type.id = product.type_id
GROUP BY type.name
ORDER BY COUNT(product.name);

/*6*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
WHERE type.name IN ('СЫР', 'МОЛОКО');

/*7 Количество поменял на 2(в задании 10), 
чтобы для моих данных, хоть что-то отбрасывалось*/
SELECT type.name AS "Тип"
FROM product JOIN type ON type.id = product.type_id
GROUP BY type.name
HAVING COUNT(product.name) < 2;

/*8*/
SELECT product.name AS "Наименование",
type.name AS "Тип", product.expired_date AS "Срок годности", product.price AS "Цена"
FROM product JOIN type ON type.id = product.type_id
ORDER BY product.expired_date DESC, product.price;


       
