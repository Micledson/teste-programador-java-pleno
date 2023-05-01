CREATE SEQUENCE access_log_id_sec start 1 increment 1;
CREATE SEQUENCE hibernate_sequence start 1 increment 1;
CREATE TABLE product
(
    id          serial primary key,
    description varchar(100),
    unities     int,
    price       double precision

);
