CREATE TABLE street
(
    street_id SERIAL      NOT NULL,
    name        VARCHAR(32) NOT NULL,
    PRIMARY KEY (street_id),
    UNIQUE (name)
);