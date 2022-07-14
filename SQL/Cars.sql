/*1*/
CREATE TABLE car_bodies(
     id SERIAL PRIMARY KEY,
     name TEXT
);
     
CREATE TABLE car_engines(
     id SERIAL PRIMARY KEY,
     name TEXT
);
     
CREATE TABLE car_transmissions(
     id SERIAL PRIMARY KEY,
     name TEXT
);
     
CREATE TABLE cars(
     id SERIAL PRIMARY KEY,
     name TEXT,
     body_id INT REFERENCES car_bodies(id),
     engine_id INT REFERENCES car_engines(id),
     transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name)
VALUES ('седан'), ('хэтчбек'), ('пикап');

INSERT INTO car_engines(name)
VALUES ('4G63'), ('D-series'), ('2JZ-GE');

INSERT INTO car_transmissions(name)
VALUES ('Механическая коробка'), 
       ('Автоматическая коробка'), 
       ('Роботизированная коробка');
       
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('BMW', 3, 1, 1), 
       ('TOYOTA', 1, 3, 2), 
       ('HONDA', 1, 2, 1),
       ('LADA', 1, null, 3);

/*2*/
/*2.1*/
SELECT car.id id, 
       car.name car_name, 
       body.name body_name, 
       engine.name engine_name,
       transmission.name transmission_name
FROM cars car LEFT JOIN car_bodies body ON car.body_id = body.id
              LEFT JOIN car_engines engine ON car.engine_id = engine.id
              LEFT JOIN car_transmissions transmission 
                                ON car.transmission_id = transmission.id
                                
/*2.2*/
SELECT body.name
FROM car_bodies body
WHERE body.id NOT IN (SELECT body_id FROM cars);

/*2.3*/
SELECT engine.name
FROM car_engines engine
WHERE engine.id NOT IN (SELECT engine_id FROM cars);

/*2.4*/
SELECT transmission.name
FROM car_transmissions transmission
WHERE transmission.id NOT IN (SELECT transmission_id FROM cars);