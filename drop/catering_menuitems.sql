create table menuitems
(
    id          int auto_increment
        primary key,
    menu_id     int      not null,
    section_id  int      null,
    description tinytext null,
    recipe_id   int      not null,
    position    int      null
);

INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (96, 80, 0, 'Croissant vuoti', 9, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (97, 80, 0, 'Croissant alla marmellata', 9, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (98, 80, 0, 'Pane al cioccolato mignon', 10, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (99, 80, 0, 'Panini al latte con prosciutto crudo', 12, 4);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (100, 80, 0, 'Panini al latte con prosciutto cotto', 12, 5);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (101, 80, 0, 'Panini al latte con formaggio spalmabile alle erbe', 12, 6);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (102, 80, 0, 'Girelle all''uvetta mignon', 11, 3);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (103, 82, 0, 'Biscotti', 13, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (104, 82, 0, 'Lingue di gatto', 14, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (105, 82, 0, 'Bigné alla crema', 15, 3);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (106, 82, 0, 'Bigné al caffè', 15, 4);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (107, 82, 0, 'Pizzette', 16, 5);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (108, 82, 0, 'Croissant al prosciutto crudo mignon', 9, 6);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (109, 82, 0, 'Tramezzini tonno e carciofini mignon', 17, 7);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (112, 86, 41, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (113, 86, 41, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (114, 86, 41, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (115, 86, 42, 'Penne alla messinese', 5, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (116, 86, 42, 'Risotto alla zucca', 20, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (117, 86, 43, 'Salmone al forno', 8, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (118, 86, 44, 'Sorbetto al limone', 18, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (119, 86, 44, 'Torta Saint Honoré', 19, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (120, 87, 45, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (121, 87, 45, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (122, 87, 45, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (123, 87, 47, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (124, 87, 47, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (125, 87, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (126, 87, 0, 'Penne al sugo di baccalà', 5, 1);
