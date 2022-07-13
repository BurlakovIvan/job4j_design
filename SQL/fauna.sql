create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date) 
VALUES ('fish1', '15220', '2016-09-01'),
       ('spider', '12542', '1850-10-12'),
       ('Truefish2', '12021552', '1905-08-11'),
       ('wolf', '36500221', '2000-12-14'),
       ('cat', '5458548', '1470-04-08'),
       ('fox', '455125', '1951-01-01');

/*4.2.1*/
SELECT * FROM fauna WHERE name LIKE '%fish%';
/*4.2.2*/
SELECT * FROM fauna WHERE avg_age BETWEEN 10000 AND 21000;
/*4.2.3*/
SELECT * FROM fauna WHERE discovery_date IS NULL;
/*4.2.4*/
SELECT * FROM fauna WHERE discovery_date < '1950-01-01';

