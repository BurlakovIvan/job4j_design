drop table if exists products;

create table if not exists products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* 2.1
*/
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
    
insert into products (name, producer, count, price)
   VALUES ('product_1', 'producer_1', 3, 50);
    
SELECT * FROM products;  

drop trigger tax_trigger on products;

/* 2.2
*/
create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    before insert
    on products
    for each row
    execute procedure discount();
    
insert into products (name, producer, count, price)
   VALUES ('product_2', 'producer_2', 10, 80);
    
SELECT * FROM products;  

drop trigger discount_trigger on products;

/* 2.3
*/
drop table if exists history_of_price;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_history()
    returns trigger as
$$
    BEGIN
    insert into history_of_price(name, price, date)
        values (NEW.name, NEW.price, NOW());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_price_trigger
    after insert 
    on products
    for each row
    execute procedure insert_history();
    
insert into products (name, producer, count, price)
   VALUES ('product_3', 'producer_3', 2, 100);
   
SELECT * FROM history_of_price;   
