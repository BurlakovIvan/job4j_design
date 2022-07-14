INSERT INTO role(name)
VALUES ('role1'), ('role2'), ('role3'), ('role4');

INSERT INTO rules(name)
VALUES ('rule1'), ('rule2'), ('rule3'), ('rule4');

INSERT INTO role_rules(role_id, rules_id)
VALUES (1,4), (2,3), (3,2), (4,1), (1,3), (2,4), (3,3), (4,2);

INSERT INTO users(name, role_id)
VALUES ('IVAN', 4), ('PETR',3), ('SERGEY',2), 
       ('DMITRIY',1), ('ALEXANDER',3), ('MAKSIM',4), 
       ('IGOR',3), ('OLEG',2);

INSERT INTO category(name)
VALUES ('category1'), ('category2'), ('category3'), ('category4');

INSERT INTO state(name)
VALUES ('Succes'), ('Wrong'), ('Cancel'), ('Perfectly');

INSERT INTO item(name, users_id, category_id, state)
VALUES ('item1', 5, 1, 4),
       ('item2', 7, 4, 1), 
       ('item3', 2, 2, 1), 
       ('item4', 3, 3, 3);

INSERT INTO comments(name, item_id)
VALUES ('comment1', 1),
       ('comment2', 4), 
       ('comment3', 2), 
       ('comment4', 3);

INSERT INTO attachs(name, item_id)
VALUES ('attach1', 1),
       ('attach2', 4), 
       ('attach3', 2), 
       ('attach4', 3);