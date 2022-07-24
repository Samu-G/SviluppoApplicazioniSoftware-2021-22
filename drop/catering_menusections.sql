create table menusections
(
    id       int auto_increment
        primary key,
    menu_id  int      not null,
    name     tinytext null,
    position int      null
);

INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (41, 86, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (42, 86, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (43, 86, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (44, 86, 'Dessert', 3);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (45, 87, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (46, 87, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (47, 87, 'Secondi', 2);
