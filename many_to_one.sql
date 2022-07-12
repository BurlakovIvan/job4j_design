CREATE TABLE university (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE students(
    id serial primary key,
    name varchar(255),
    university_id int REFERENCES university(id)
);

INSERT INTO university(name) VALUES ('MSU');
INSERT INTO students(name, university_id) 
           VALUES ('Ivan', 1),
           ('Petr', 1),
           ('Sergey', 1);


SELECT * FROM students;

