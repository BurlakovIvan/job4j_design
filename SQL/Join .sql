/*1*/
CREATE TABLE departments(
     department_id SERIAL PRIMARY KEY,
     name VARCHAR(250));
     
CREATE TABLE employees(
     employe_id SERIAL PRIMARY KEY,
     department_id INT REFERENCES departments(department_id),
     name VARCHAR(250));
     
INSERT INTO departments(name) 
VALUES ('MAIN'), ('LAST'), ('WITHOUT_Work');

INSERT INTO employees(name, department_id)
VALUES ('IVAN', 1),
       ('PETR', 2),
       ('SERGEY', 2),
       ('DMITRIY', 1),
       ('ALEXANDR', 2),
       ('OLEG', 1),
       ('EVGENIY', 1),
       ('VLADIMIR', 1),
       ('PAVEL', 2),
       ('ALEXEY', 1),
       ('VIKTOR', 2);
       
/*2*/
/*LEFT JOIN*/
SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM employees LEFT JOIN departments USING(department_id);

/*RIGHT JOIN*/
SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM employees RIGHT JOIN departments USING(department_id);

/*FULL JOIN*/
SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM employees FULL JOIN departments USING(department_id);

/*CROSS JOIN*/
SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM employees CROSS JOIN departments;

/*3*/
SELECT departments.name AS "Департамент"
FROM departments LEFT JOIN employees USING(department_id)
WHERE employees.name IS NULL;

/*4*/
SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM employees LEFT JOIN departments USING(department_id);

SELECT employees.name AS "Имя", departments.name AS "Департамент"
FROM departments RIGHT JOIN employees USING(department_id);

/*5*/
CREATE TABLE teens(
     teen_id SERIAL PRIMARY KEY,
     name VARCHAR(250),
     gender VARCHAR(6));
     
INSERT INTO teens(name, gender) 
VALUES ('SERGEY', 'man'), 
       ('ElENA', 'female'), 
       ('MARIYA', 'female'),
       ('DMITRIY', 'man'), 
       ('SVETLANA', 'female'), 
       ('VIKTOR', 'man');
       
SELECT teen1.name AS "Man", teen2.name As "Women"
FROM teens AS teen1 CROSS JOIN teens AS teen2
WHERE teen1.gender != teen2.gender AND teen1.gender = 'man';