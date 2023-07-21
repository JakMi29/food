CREATE TABLE food_app_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE food_app_user
(
    user_id   SERIAL        NOT NULL,
    user_name VARCHAR(32)   NOT NULL,
    email     VARCHAR(32)   NOT NULL,
    password  VARCHAR(128)  NOT NULL,
    active    BOOLEAN       NOT NULL,
    role_id    INT       NOT NULL,
    PRIMARY KEY (user_id),
     CONSTRAINT fk_role_id
                    FOREIGN KEY (role_id)
                        REFERENCES food_app_role (role_id)
);



