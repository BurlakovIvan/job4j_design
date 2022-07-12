CREATE TABLE author(
    author_id SERIAL PRIMARY KEY,
    name_author VARCHAR(50));
    
CREATE TABLE book (
    book_id SERIAL PRIMARY KEY, 
    title VARCHAR(50), 
    author_id INT REFERENCES author(author_id),  
    price DECIMAL(8,2), 
    amount INT 
);

INSERT INTO author(name_author)
VALUES ('Булгаков М.А.'), 
       ('Достоевский Ф.М.'), 
       ('Есенин С.А.'), 
       ('Пастернак Б.Л.'),
       ('Лермонтов М.Ю.');

INSERT INTO book(title, author_id, price, amount)
VALUES ('Мастер и Маргарита', 1, 1250.5, 100), 
       ('Белая гвардия', 1, 1400, 200), 
       ('Идиот', 2, 1547, 50), 
       ('Черный человек', 3, 1500, 500),
       ('Братья Карамазовы', 2, 1500, 10),
       ('Лирика', 4, 500, 1000);
 
 SELECT a.name_author AS "АВТОР", b.title AS "Название книги", b.price AS "Цена"
 FROM author AS a JOIN book AS B ON a.author_id = b.author_id;
 
 SELECT name_author AS "АВТОР", title AS "Название книги"
 FROM author a JOIN book b ON a.author_id = b.author_id;
 
 SELECT a.name_author AS "АВТОР", b.title, b.price * b.amount AS "Стоимость"
 FROM author a JOIN book b USING(author_id);
