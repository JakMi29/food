CREATE TABLE "order"
(
    order_id   SERIAL         NOT NULL,
    received_date_time        TIMESTAMP WITH TIME ZONE,
    completed_date_time       TIMESTAMP WITH TIME ZONE,
    order_number            VARCHAR(20)     NOT NULL,
    price                       NUMERIC(19, 2) NOT NULL,
    complete BOOLEAN            NOT NULL,
    customer_id                 INT                         NOT NULL,
    restaurant_id                 INT                         NOT NULL,
    PRIMARY KEY (order_id),
    UNIQUE (order_id,order_number)
);