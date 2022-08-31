drop table if exists products;

create table if not exists products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_2', 'producer_2', 6, 115);
call insert_data('product_3', 'producer_3', 4, 80);
call insert_data('product_4', 'producer_4', 8, 150);

SELECT * FROM products;

/*
продецуда удаления
*/

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products
      where id = d_id;
    END
$$;

call delete_data(2);

SELECT * FROM products;

/*
функция удаления
*/

create or replace function f_delete_data(i_count integer)
returns void
language 'plpgsql'
as
$$
    begin
      delete from products
         where count > i_count;
    end;
$$;

SELECT f_delete_data(5);

SELECT * FROM products;