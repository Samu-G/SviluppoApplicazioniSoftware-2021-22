create table events
(
    id                    int auto_increment
        primary key,
    name                  varchar(128) null,
    date_start            date         null,
    date_end              date         null,
    expected_participants int          null,
    organizer_id          int          not null
);

INSERT INTO catering.events (id, name, date_start, date_end, expected_participants, organizer_id) VALUES (1, 'Convegno Agile Community', '2020-09-25', '2020-09-25', 100, 2);
INSERT INTO catering.events (id, name, date_start, date_end, expected_participants, organizer_id) VALUES (2, 'Compleanno di Manuela', '2020-08-13', '2020-08-13', 25, 2);
INSERT INTO catering.events (id, name, date_start, date_end, expected_participants, organizer_id) VALUES (3, 'Fiera del Sedano Rapa', '2020-10-02', '2020-10-04', 400, 1);
