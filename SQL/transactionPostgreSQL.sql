/*создаем таблицу, в моем случае она уже была создана 
и заполнена некоторыми данными, поэтому код будет проигнорирован
*/
create table if not exists products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--начинаем транзакцию
begin transaction;
--добавляем данные в транзакции
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);
-- устанавливаем первую точку сохранения с именем first
savepoint first;
--удаляем данные в таблице у которых цена ниже 100
delete from products where price < 100;
--добавляем строку в таблицу
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 100, 50);
--выводим результат на текущей момент
select * from products;
--устанавливаем вторую точку сохранения
savepoint second;
--еще добавляем данные в таблицу
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 1, 5);
--выводим текущее состояние после добавления
select * from products;
--откатываемся на вторую точку сохранения
rollback to second;
--выводим результат и видим, что последней добавленной строчки нет
select * from products;
--удаляем все записи в таблице и удаляем саму таблицу
delete from products;
drop table products;
--откатываемся на вторую точку сохранения
rollback to second;
--выводим данные из таблицы и видим, что таблица не была удалена
select * from products;
--откатываемся до первой точки сохранения
rollback to first;

select * from products;
--отменяем все изменения в транзакции
rollback transaction;
--выводим значения в таблице, и видим что все, что мы меняли не сохранилось
select * from products;