create table tasks
(
    id             int auto_increment
        primary key,
    name           varchar(128) not null,
    completed      tinyint(1)   not null,
    quantity       int          not null,
    estimated_time int          not null,
    ready          tinyint(1)   not null,
    sum_sheet_id   int          not null,
    cook_id        int          not null
);

