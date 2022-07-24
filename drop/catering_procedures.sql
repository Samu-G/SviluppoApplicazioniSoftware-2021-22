create table procedures
(
    id             int auto_increment
        primary key,
    name           varchar(128)  not null,
    instruction    varchar(1024) null,
    tag            varchar(8)    null,
    time           int           null,
    quantity       int           null,
    owner_id       int           not null,
    task_id        int           not null,
    procedure_type int           not null
);

