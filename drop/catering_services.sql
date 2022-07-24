create table services
(
    id                    int auto_increment
        primary key,
    event_id              int           not null,
    name                  varchar(128)  null,
    proposed_menu_id      int default 0 not null,
    approved_menu_id      int default 0 null,
    service_date          date          null,
    time_start            time          null,
    time_end              time          null,
    expected_participants int           null
);

INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (1, 2, 'Cena', 86, 0, '2020-08-13', '20:00:00', '23:30:00', 25);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (2, 1, 'Coffee break mattino', 0, 80, '2020-09-25', '10:30:00', '11:30:00', 100);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (3, 1, 'Colazione di lavoro', 0, 0, '2020-09-25', '13:00:00', '14:00:00', 80);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (4, 1, 'Coffee break pomeriggio', 0, 82, '2020-09-25', '16:00:00', '16:30:00', 100);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (5, 1, 'Cena sociale', 0, 0, '2020-09-25', '20:00:00', '22:30:00', 40);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (6, 3, 'Pranzo giorno 1', 0, 0, '2020-10-02', '12:00:00', '15:00:00', 200);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (7, 3, 'Pranzo giorno 2', 0, 0, '2020-10-03', '12:00:00', '15:00:00', 300);
INSERT INTO catering.services (id, event_id, name, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants) VALUES (8, 3, 'Pranzo giorno 3', 0, 0, '2020-10-04', '12:00:00', '15:00:00', 400);
