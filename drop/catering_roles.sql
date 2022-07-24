create table roles
(
    id   char                            not null
        primary key,
    role varchar(128) default 'servizio' not null
);

INSERT INTO catering.roles (id, role) VALUES ('c', 'cuoco');
INSERT INTO catering.roles (id, role) VALUES ('h', 'chef');
INSERT INTO catering.roles (id, role) VALUES ('o', 'organizzatore');
INSERT INTO catering.roles (id, role) VALUES ('s', 'servizio');
