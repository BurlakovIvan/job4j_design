CREATE TABLE students(
    id serial primary key,
    name varchar(255)
);

create table subjects(
     id serial primary key,
     name varchar(255)
 );
 
 create table subjects_students(
     id serial primary key,
     student_id int references students(id),
     subjects_id int references subjects(id)
 );

INSERT INTO students(name)
           VALUES ('Ivan',
           ('Petr'),
           ('Sergey');
INSERT INTO subjects(name) VALUES ('Math'),
                                  ('Literature'),
                                  ('Biology');
INSERT INTO subjects_students(student_id, subjects_id) VALUES (1, 1), 
                                                              (1, 2), 
                                                              (1, 3),
                                                              (2, 2), 
                                                              (2, 3),
                                                              (3, 3);


SELECT * FROM subjects_students;

