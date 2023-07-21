CREATE TABLE restaurant
(
    restaurant_id          SERIAL          NOT NULL,
    name             VARCHAR(20)     NOT NULL,
    food_category             VARCHAR(20)     NOT NULL,
    phone            VARCHAR(32) NOT NULL,
    email            VARCHAR(32) NOT NULL,
    description            VARCHAR(256) NOT NULL,
    address_id       INT,
    restaurant_owner_id  INT,
        PRIMARY KEY (restaurant_id),
        UNIQUE (email,phone,address_id,name),
        CONSTRAINT fk_customer_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id),
	 CONSTRAINT fk_restaurant_owner_id
            FOREIGN KEY (restaurant_owner_id)
                REFERENCES restaurant_owner (restaurant_owner_id)
);