CREATE TABLE orders
(
    id          serial primary key,
    date        DATE NOT NULL,
    description text NOT NULL,
    total_value double precision
);
