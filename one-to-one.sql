CREATE TABLE student_card(
     id serial primary key,
     number INT
 );
CREATE TABLE students(
    id serial primary key,
    name varchar(255),
    student_card_id int REFERENCES student_card(id) unique
);

INSERT INTO student_card(number) VALUES (123),
                                        (345),
                                        (678);
INSERT INTO students(name, student_card_id)
           VALUES ('Ivan', 1),
           ('Petr', 2),
           ('Sergey', 3);

