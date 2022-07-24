create table summarysheet
(
    id         int auto_increment
        primary key,
    name       varchar(128) not null,
    owner_id   int          not null,
    service_id int          not null,
    locked     tinyint(1)   not null,
    constraint summarysheet_service_id_uindex
        unique (service_id)
);

