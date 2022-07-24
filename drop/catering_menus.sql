create table menus
(
    id        int auto_increment
        primary key,
    title     tinytext             null,
    owner_id  int                  null,
    published tinyint(1) default 0 null
);

INSERT INTO catering.menus (id, title, owner_id, published) VALUES (80, 'Coffee break mattutino', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (82, 'Coffee break pomeridiano', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (86, 'Cena di compleanno pesce', 3, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (87, 'Titolo Nuovo', 2, 1);
