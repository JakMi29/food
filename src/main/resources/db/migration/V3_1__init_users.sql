ALTER TABLE customer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES food_app_user (user_id);

ALTER TABLE restaurant_owner
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES food_app_user (user_id);

insert into food_app_role (role) values ('CUSTOMER'), ('RESTAURANT_OWNER'), ('REST_API');

insert into food_app_user (user_name, email, password, active,role_id)
values
('customerJohn', 'john@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,1),
('customerEmily', 'emily@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,1);

insert into food_app_user (user_name, email, password, active,role_id)
values
('ownerJames', 'james@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerOlivia', 'olivia@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerBenjamin', 'benjamin@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerSophia', 'sophia@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerWilliam', 'william@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerAva', 'ava@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2),
('ownerJoseph', 'joseph@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,2);
insert into food_app_user (user_name, email, password, active,role_id)
values
('apiTest', 'apiTest@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true,3);

UPDATE customer SET user_id = 1 WHERE customer_id = 1;
UPDATE customer SET user_id = 1 WHERE customer_id = 2;

UPDATE restaurant_owner SET user_id = 3 WHERE restaurant_owner_id = 1;
UPDATE restaurant_owner SET user_id = 4 WHERE restaurant_owner_id = 2;
UPDATE restaurant_owner SET user_id = 5 WHERE restaurant_owner_id = 3;
UPDATE restaurant_owner SET user_id = 6 WHERE restaurant_owner_id = 4;
UPDATE restaurant_owner SET user_id = 7 WHERE restaurant_owner_id = 5;
UPDATE restaurant_owner SET user_id = 8 WHERE restaurant_owner_id = 6;
UPDATE restaurant_owner SET user_id = 9 WHERE restaurant_owner_id = 7;



ALTER TABLE customer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE restaurant_owner
ALTER COLUMN user_id SET NOT NULL;