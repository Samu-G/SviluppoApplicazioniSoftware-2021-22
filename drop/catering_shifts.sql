create table shifts
(
    id           int auto_increment
        primary key,
    date         date       not null,
    begin_time   time       not null,
    end_time     time       not null,
    is_full      tinyint(1) not null,
    is_concluded tinyint(1) not null
);

INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (1, '2022-07-18', '09:00:00', '13:00:00', 0, 1);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (2, '2022-07-18', '18:00:00', '22:00:00', 0, 1);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (3, '2022-07-19', '10:00:00', '14:00:00', 1, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (4, '2022-07-19', '16:00:00', '20:00:00', 0, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (5, '2022-07-21', '09:00:00', '13:00:00', 1, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (6, '2022-07-21', '18:00:00', '22:00:00', 0, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (7, '2022-07-22', '10:00:00', '14:00:00', 0, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (8, '2022-07-22', '20:00:00', '00:00:00', 0, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (9, '2022-07-23', '09:00:00', '17:00:00', 1, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (10, '2022-07-24', '07:00:00', '11:00:00', 0, 0);
INSERT INTO catering.shifts (id, date, begin_time, end_time, is_full, is_concluded) VALUES (11, '2022-07-24', '16:00:00', '20:00:00', 1, 0);
