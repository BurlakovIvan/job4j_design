CREATE TABLE role(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE rules(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE role_rules(
    id SERIAL PRIMARY KEY,
    role_id INT REFERENCES role(id),
    rules_id INT REFERENCES rules(id)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name TEXT,
    role_id INT REFERENCES role(id)
);

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name TEXT   
);

CREATE TABLE state(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    name TEXT,
    users_id INT REFERENCES users(id),
    category_id INT REFERENCES category(id),
    state INT REFERENCES state(id)
);

CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    name TEXT,
    item_id INT REFERENCES item(id) 
);

CREATE TABLE attachs(
    id SERIAL PRIMARY KEY,
    name TEXT,
    item_id INT REFERENCES item(id) 
);