CREATE TABLE order_meal
(
    order_meal_id          SERIAL          NOT NULL,
    order_id            INT                 NOT NULL,
    quantity            INT                 NOT NULL,
    meal_id             INT                 NOT NULL,
    price            NUMERIC(19, 2)  NOT NULL,
     PRIMARY KEY (order_meal_id),
      CONSTRAINT fk_order_id
          FOREIGN KEY (order_id)
              REFERENCES "order" (order_id),
       CONSTRAINT fk_meal_id
                FOREIGN KEY (meal_id)
                    REFERENCES meal (meal_id)
);