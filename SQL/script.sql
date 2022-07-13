CREATE TABLE student(
       id serial primary key,
       fullname VARCHAR(100),
       dateOfBirth DATE,
       mark INT);

INSERT INTO student(fullname, dateOfBirth, mark) 
VALUES ('Petrov Ivan', '1998-07-26', 4),
       ('Ivanov Petr', '2000-11-14', 3),
       ('Serveev Sergey', '2001-02-14', 5);

UPDATE student SET mark = 4
WHERE mark < 4;

DELETE FROM student  
WHERE mark > 4;

SELECT * FROM student;

