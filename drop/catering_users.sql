create table users
(
    id       int auto_increment
        primary key,
    username varchar(128) default '' not null
);

INSERT INTO catering.users (id, username) VALUES (1, 'Carlin');
INSERT INTO catering.users (id, username) VALUES (2, 'Lidia');
INSERT INTO catering.users (id, username) VALUES (3, 'Tony');
INSERT INTO catering.users (id, username) VALUES (4, 'Marinella');
INSERT INTO catering.users (id, username) VALUES (5, 'Guido');
INSERT INTO catering.users (id, username) VALUES (6, 'Antonietta');
INSERT INTO catering.users (id, username) VALUES (7, 'Paola');
INSERT INTO catering.users (id, username) VALUES (8, 'Silvia');
INSERT INTO catering.users (id, username) VALUES (9, 'Marco');
INSERT INTO catering.users (id, username) VALUES (10, 'Piergiorgio');
