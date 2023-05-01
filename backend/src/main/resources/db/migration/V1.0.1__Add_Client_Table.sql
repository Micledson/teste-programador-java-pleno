CREATE TABLE client
(
    id    serial primary key,
    name  VARCHAR(100),
    cpf   VARCHAR(20) UNIQUE,
    phone VARCHAR(20),
    email VARCHAR(50) UNIQUE
);
