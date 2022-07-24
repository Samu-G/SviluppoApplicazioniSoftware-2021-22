create table userroles
(
    user_id int              not null,
    role_id char default 's' not null
);

INSERT INTO catering.userroles (user_id, role_id) VALUES (1, 'o');
INSERT INTO catering.userroles (user_id, role_id) VALUES (2, 'o');
INSERT INTO catering.userroles (user_id, role_id) VALUES (2, 'h');
INSERT INTO catering.userroles (user_id, role_id) VALUES (3, 'h');
INSERT INTO catering.userroles (user_id, role_id) VALUES (4, 'h');
INSERT INTO catering.userroles (user_id, role_id) VALUES (4, 'c');
INSERT INTO catering.userroles (user_id, role_id) VALUES (5, 'c');
INSERT INTO catering.userroles (user_id, role_id) VALUES (6, 'c');
INSERT INTO catering.userroles (user_id, role_id) VALUES (7, 'c');
INSERT INTO catering.userroles (user_id, role_id) VALUES (8, 's');
INSERT INTO catering.userroles (user_id, role_id) VALUES (9, 's');
INSERT INTO catering.userroles (user_id, role_id) VALUES (10, 's');
INSERT INTO catering.userroles (user_id, role_id) VALUES (7, 's');
