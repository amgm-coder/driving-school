DROP TABLE IF EXISTS t_user_info;

CREATE TABLE t_user_info
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(250) NOT NULL,
    last_name   VARCHAR(250) NOT NULL,
    career      VARCHAR(250) DEFAULT NULL,
    create_time TIMESTAMP    DEFAULT current_timestamp
);

INSERT INTO t_user_info (first_name, last_name, career, create_time)
VALUES ('Aliko', 'Dangote', 'Billionaire Industrialist', '2023-01-01 10:10:01'),
       ('Bill', 'Gates', 'Billionaire Tech Entrepreneur', '2023-01-01 10:10:01'),
       ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate', '2023-01-01 10:10:01');


DROP TABLE IF EXISTS t_admin;
CREATE TABLE t_admin
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    sex         VARCHAR(250) NOT NULL,
    pwd         VARCHAR(250) DEFAULT NULL,
    phone       VARCHAR(250) DEFAULT NULL,
    id_card     VARCHAR(250) DEFAULT NULL,
    birth       VARCHAR(250) DEFAULT NULL,
    create_time TIMESTAMP    DEFAULT current_timestamp,
    update_time TIMESTAMP    DEFAULT current_timestamp
);

INSERT INTO t_admin (`name`, sex, pwd, phone, id_card, birth, create_time, update_time)
VALUES ('z3', '男', '123456', '123456', '123123', '2000-01-01', '2023-01-01 10:10:01', '2023-01-01 10:10:01'),
       ('w5', '男', '123456', '123', '123123', '2000-01-01', '2023-01-01 10:10:01', '2023-01-01 10:10:01')
;

DROP TABLE IF EXISTS t_car;
CREATE TABLE t_car
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    mark        VARCHAR(250) NOT NULL,
    brand       VARCHAR(250) NOT NULL,
    subject     VARCHAR(250) DEFAULT NULL,
    item        VARCHAR(250) DEFAULT NULL,
    car_picture VARCHAR(250) DEFAULT NULL,
    create_time TIMESTAMP    DEFAULT current_timestamp,
    update_time TIMESTAMP    DEFAULT current_timestamp
);

INSERT INTO t_car (mark, brand, `subject`, item, car_picture, create_time, update_time)
VALUES ('标识1', '大众', '123', '123', '', '2023-01-01 10:10:01', '2023-01-01 10:10:01'),
       ('标识2', '大众', '1231', '1231', '', '2023-01-01 10:10:01', '2023-01-01 10:10:01')
;

DROP TABLE IF EXISTS t_appointment;
CREATE TABLE t_appointment
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    start_time TIMESTAMP DEFAULT current_timestamp,
    end_time   TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO t_appointment (start_time, end_time)
VALUES ('2023-01-01 10:10:01', '2023-01-02 10:10:01'),
       ('2023-01-05 10:10:01', '2023-01-06 10:10:01')
;

DROP TABLE IF EXISTS t_reservation;
CREATE TABLE t_reservation
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    student_id     INT NOT NULL,
    car_id         INT NOT NULL,
    appointment_id INT       DEFAULT NULL,
    create_time    TIMESTAMP DEFAULT current_timestamp,
    update_time    TIMESTAMP DEFAULT current_timestamp
);

DROP TABLE IF EXISTS t_trainee;
CREATE TABLE t_trainee
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    sex         VARCHAR(250) NOT NULL,
    pwd         VARCHAR(250) DEFAULT NULL,
    phone       VARCHAR(250) DEFAULT NULL,
    entry_fee   INT          DEFAULT NULL,
    id_card     VARCHAR(250) DEFAULT NULL,
    birth       VARCHAR(250) DEFAULT NULL,
    create_time TIMESTAMP    DEFAULT current_timestamp,
    update_time TIMESTAMP    DEFAULT current_timestamp
);

INSERT INTO t_trainee (`name`, sex, pwd, phone, entry_fee, id_card, birth, create_time, update_time)
VALUES ('z3', '男', '123456', '123456', 1212, '123123', '2000-01-01', '2023-01-01 10:10:01', '2023-01-01 10:10:01'),
       ('w5', '男', '123456', '123', 1212, '123123', '2000-01-01', '2023-01-01 10:10:01', '2023-01-01 10:10:01')
;