CREATE TABLE order_product
(
    id         serial NOT NULL,
    order_id   int8   NOT NULL,
    product_id int8   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE
);
