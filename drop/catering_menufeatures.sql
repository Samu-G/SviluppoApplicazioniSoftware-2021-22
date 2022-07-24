create table menufeatures
(
    menu_id int                     not null,
    name    varchar(128) default '' not null,
    value   tinyint(1)   default 0  null
);

INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (80, 'Richiede cuoco', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (80, 'Buffet', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (80, 'Richiede cucina', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (80, 'Finger food', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (80, 'Piatti caldi', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (82, 'Richiede cuoco', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (82, 'Buffet', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (82, 'Richiede cucina', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (82, 'Finger food', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (82, 'Piatti caldi', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (86, 'Richiede cuoco', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (86, 'Buffet', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (86, 'Richiede cucina', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (86, 'Finger food', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (86, 'Piatti caldi', 0);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (87, 'Richiede cuoco', 1);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (87, 'Buffet', 1);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (87, 'Richiede cucina', 1);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (87, 'Finger food', 1);
INSERT INTO catering.menufeatures (menu_id, name, value) VALUES (87, 'Piatti caldi', 1);
