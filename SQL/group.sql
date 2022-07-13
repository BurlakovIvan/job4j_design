/*1*/
create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
/*2*/
INSERT INTO devices(name, price) 
VALUES ('LaptopNom1', 1500),
       ('LaptopNom2', 6000),
       ('LaptopNom1', 2000),
       ('LaptopNom2', 1400),
       ('LaptopNom2', 12000);

INSERT INTO people(name) 
VALUES ('Ivan'),
       ('Sergey'),
       ('Petr'),
       ('Alexander'),
       ('Igor');
       
INSERT INTO devices_people(people_id, device_id)
VALUES (1, 3), (3, 5), (2, 2), (4, 1), (5, 4), (1, 2), (5, 1), (3, 4);

/*3*/
SELECT name AS "Устройство", round(AVG(price)::numeric, 2) AS "Средняя цена"
FROM devices
GROUP BY name;

SELECT AVG(price) AS "Средняя цена"
FROM devices;

/*4*/
SELECT people.name AS "Имя", AVG(devices.price) AS "Средняя цена"
FROM people JOIN devices_people ON people.id = devices_people.people_id
            JOIN devices ON devices.id = devices_people.device_id
GROUP BY people.name; 
            
/*5*/
SELECT people.name AS "Имя", AVG(devices.price) AS "Средняя цена"
FROM people JOIN devices_people ON people.id = devices_people.people_id
            JOIN devices ON devices.id = devices_people.device_id
GROUP BY people.name
HAVING AVG(devices.price) > 5000;
