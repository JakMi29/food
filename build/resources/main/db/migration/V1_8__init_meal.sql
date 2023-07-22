CREATE TABLE meal
(
    meal_id          SERIAL          NOT NULL,
    name             VARCHAR(20)     NOT NULL,
    description      VARCHAR(64)     NOT NULL,
    category      VARCHAR(32)     NOT NULL,
    price            NUMERIC(19, 2)  NOT NULL,
    restaurant_id       INT             NOT NULL,
    image      VARCHAR(128)     NOT NULL,
     PRIMARY KEY (meal_id),
      CONSTRAINT fk_meal_restaurant
          FOREIGN KEY (restaurant_id)
              REFERENCES restaurant (restaurant_id)
);