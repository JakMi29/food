CREATE TABLE address
(
    address_id  SERIAL      NOT NULL,
    country     VARCHAR(32) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    postal_code VARCHAR(32) NOT NULL,
    street_id   INT NOT NULL,
    house_number     VARCHAR(32) NOT NULL,
    PRIMARY KEY (address_id),
       CONSTRAINT fk_customer_address
            FOREIGN KEY (street_id)
                REFERENCES street (street_id)
);