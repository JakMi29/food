INSERT INTO street (name)
VALUES ('Sikorskiego'), ('Korfantego'), ('Witosa'),('Moniuszki'),('Limanowskiego'),('Chopina'),
('Prusa'),('Mickiewicza'),('Sienkiewicza'),('Reymonta');

INSERT INTO address (country, city, postal_code, street_id, house_number)
VALUES
  ('Poland', 'Wroclaw', '52-132', 1, '4a/12'),
  ('Poland', 'Wroclaw', '52-132', 2, '5b/3'),
  ('Poland', 'Wroclaw', '52-132', 3, '3c/1'),
  ('Poland', 'Wroclaw', '52-132', 4, '2b/3'),
  ('Poland', 'Wroclaw', '52-132', 5, 'g/3'),
  ('Poland', 'Wroclaw', '52-132', 6, '45/3'),
  ('Poland', 'Wroclaw', '52-132', 7, '11a/3'),
  ('Poland', 'Wroclaw', '52-132', 4, '6g/3'),
  ('Poland', 'Wroclaw', '52-132', 2, '3a/3'),
  ('Poland', 'Wroclaw', '52-132', 1, '2d/3');

insert into customer (name, user_name, surname, phone, email, address_id)
values
('John', 'customerJohn', 'Smith', '+48 670 204 936', 'john@gmail.com', 1),
('Emily', 'customerEmily', 'Johnson', '+48 650 278 236', 'emily@gmail.com', 2);

insert into restaurant_owner (name,user_name, surname, phone, email)
values
('James', 'ownerJames','Davis', '+48 987 789 789', 'james@gmail.com'),
('Olivia', 'ownerOlivia','Taylor', '+48 547 312 329', 'olivia@gmail.com'),
('Benjamin', 'ownerOlivia','Wilson', '+48 987 789 312', 'benjamin@gmail.com'),
('Sophia', 'ownerSophia','Anderson', '+48 534 789 679', 'sophia@gmail.com'),
('William', 'ownerWilliam','Thomas', '+48 987 299 523', 'william@gmail.com'),
('Ava', 'ownerAva','Robinson', '+48 987 765 789', 'ava@gmail.com'),
('Joseph', 'ownerJoseph','Martinez', '+48 987 789 9769', 'joseph@gmail.com');

insert into restaurant (name, food_category, phone, email, address_id, restaurant_owner_id,description)
values
('Flavors of est', 'All', '+48 987 789 789', 'esta@gmail.com', 1, 1,'In our restaurant you can eat delicious and cheap. Not only traditional Polish dishes but also delicious Italian pizza.'),
('Da Grasso', 'Pizza', '+48 534 789 789', 'daGrasso@gmail.com', 2, 2,'In our restaurant you can eat delicious stone oven pizzas.'),
('Sushi Master', 'Sushi', '+48 987 765 789', 'sushi@gmail.com', 3, 3,'In our restaurant you can eat delicious sushi and Asian snacks.'),
('Polish Pot', 'Polish cuisine ', '+48 987 879 789', 'polishPot@gmail.com', 4, 4,'In our restaurant you can eat delicious regional meals.'),
('La Italiano', 'Italian cuisine', '+48987 789 253', 'laItaliano@gmail.com', 5, 5,'In our restaurant you can eat delicious Italian dishes and pizzas fired in a wood-fired stone oven.'),
('Asian Food', 'Asian cuisine', '+48 412 134 789', 'azja@gmail.com', 6, 6,'In our restaurant you can eat many interesting dishes of Asian cuisine'),
('World Cuisine', 'All', '+48 143 789 413', 'worldCuisine@gmail.com', 7, 7,'In our restaurant you can eat the most popular dishes from around the world.');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Bigos', 'appetizer', '16', 'traditional Polish bigos', 1,'/images/mealPhotos/bigos.png'),
('Pierogi', 'flour dishes', '16', 'homemade pierogi 8 pieces', 1,'/images/mealPhotos/63.png'),
('Margaritta', 'pizza', '18', 'tomato sauce, ser mozzarella, oregano', 1,'/images/mealPhotos/margherita.png'),
('Kotlet schabowy', 'main course', '20', 'kotlet schabowy with potatoes and pigs', 1,'/images/mealPhotos/schabowy.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 1,'/images/mealPhotos/lemonade.png'),
('Spaghetti bolognese', 'main course', '18', 'spaghetti bolognese with homemade noodles', 1,'/images/mealPhotos/spaghetti.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Margaritta', 'pizza', '18', 'tomato sauce, ser mozzarella, oregano', 2,'/images/mealPhotos/margherita.png'),
('Parme', 'pizza', '22', 'tomato sauce, ser mozzarella, oregano, arugula, Parma ham', 2,'/images/mealPhotos/63.png'),
('Salame', 'pizza', '20', 'tomato sauce, ser mozzarella, oregano, salami, mushrooms', 2,'/images/mealPhotos/salame.png'),
('Capricciosa', 'pizza', '20', 'tomato sauce, ser mozzarella, oregano, ham, mushrooms', 2,'/images/mealPhotos/spaghetti.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 2,'/images/mealPhotos/lemonade.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Nigiri', 'sushi', '18', '4 nigiri rolls', 3,'/images/mealPhotos/nigiri.png'),
('Urumaki', 'sushi', '22', '8 urumaki rolls', 3,'/images/mealPhotos/urumaki.png'),
('Maki', 'sushi', '16', '4 maki rolls', 3,'/images/mealPhotos/maki.png'),
('Rolls', 'appetizer', '12', '6 rolls', 3,'/images/mealPhotos/rolls.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 3,'/images/mealPhotos/lemonade.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Bigos', 'appetizer', '16', 'traditional Polish bigos', 4,'/images/mealPhotos/bigos.png'),
('Pierogi', 'flour dishes', '16', 'homemade pierogi 8 pieces', 4,'/images/mealPhotos/63.png'),
('Kotlet schabowy', 'main course', '20', 'kotlet schabowy with potatoes and pigs', 4,'/images/mealPhotos/schabowy.png'),
('Żurek', 'soup', '16', 'polish traditional soup', 4,'/images/mealPhotos/zurek.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 4,'/images/mealPhotos/lemonade.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Margaritta', 'pizza', '18', 'tomato sauce, ser mozzarella, oregano', 5,'/images/mealPhotos/margherita.png'),
('Parme', 'pizza', '22', 'tomato sauce, ser mozzarella, oregano, arugula, Parma ham', 5,'/images/mealPhotos/63.png'),
('Spaghetti bolognese ', 'main course', '18', 'spaghetti bolognese with homemade noodles', 5,'/images/mealPhotos/spaghetti.png'),
('Tiramisu', 'dessert', '10', 'traditional Italian dessert', 5,'/images/mealPhotos/tiramisu.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 5,'/images/mealPhotos/lemonade.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Ramen', 'soup', '18', 'traditional Japan soup', 6,'/images/mealPhotos/ramen.png'),
('Kimchi', 'salad', '14', 'fermented vegetable salad', 6,'/images/mealPhotos/kimchi.png'),
('Okonomiyaki', 'main course', '18', 'vegetable pie', 6,'/images/mealPhotos/okonomiyaki.png'),
('Rolls', 'appetizer', '12', '6 rolls', 6,'/images/mealPhotos/rolls.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 6,'/images/mealPhotos/lemonade.png');

insert into meal (name, category, price, description, restaurant_id,image)
values
('Ratatouille', 'main course', '18', 'traditional French dish', 7,'/images/mealPhotos/ratatouille.png'),
('Ramen', 'soup', '18', 'traditional Japan soup', 7,'/images/mealPhotos/ramen.png'),
('Kotlet schabowy', 'main course', '20', 'kotlet schabowy with potatoes and pigs', 7,'/images/mealPhotos/schabowy.png'),
('Spaghetti bolognese ', 'main course', '18', 'spaghetti bolognese with homemade noodles', 7,'/images/mealPhotos/spaghetti.png'),
('Lemonade', 'drink', '8', 'freshly squeezed lemonade', 7,'/images/mealPhotos/lemonade.png');

insert into "order" (order_number, received_date_time, price, complete, customer_id, restaurant_id)
values
('ORD62023162424321', '2020-12-10 19:00:00', '32', false, 1, 1),
('ORD62023162424322', '2020-12-13 19:00:00', '36', false, 1, 1),
('ORD62023162424323', '2023-06-25 20:38:30', '40', false, 1, 2),
('ORD62023162424324', '2023-06-25 20:38:30', '60', false, 1, 2),
('ORD62023162424325', '2023-07-17 20:38:30', '40', false, 1, 3),
('ORD62023162424326', '2023-06-25 20:38:30', '28', false, 1, 3),
('ORD62023162424327', '2023-06-25 20:38:30', '32', false, 1, 4),
('ORD62023162424328', '2023-06-25 20:38:30', '24', false, 1, 4),
('ORD62023162424329', '2023-06-21 20:34:30', '40', false, 1, 5),
('ORD62023162424330', '2023-06-25 20:38:30', '18', false, 1, 5),
('ORD62023162424331', '2023-06-25 20:38:30', '32', false, 1, 6),
('ORD62023162424332', '2023-06-25 20:38:30', '30', false, 1, 6),
('ORD62023162424333', '2023-06-25 20:38:30', '36', false, 1, 7),
('ORD62023162424334', '2023-06-25 12:38:30', '26', false, 1, 7),
('ORD62023162424335', '2020-12-10 19:00:00', '32', false, 2, 1),
('ORD62023162424336', '2020-12-10 19:00:00', '36', false, 2, 1),
('ORD62023162424337', '2023-06-22 20:38:30', '40', false, 2, 2),
('ORD62023162424338', '2023-06-25 20:38:30', '60', false, 2, 2),
('ORD62023162424339', '2023-05-25 20:38:30', '40', false, 2, 3),
('ORD62023162424340', '2023-06-28 11:38:30', '28', false, 2, 3),
('ORD62023162424341', '2023-06-25 20:38:30', '32', false, 2, 4),
('ORD62023162424342', '2023-06-25 20:38:40', '24', false, 2, 4),
('ORD62023162424343', '2023-04-25 20:38:30', '40', false, 2, 5),
('ORD62023162424344', '2023-06-25 20:38:30', '18', false, 2, 5),
('ORD62023162424345', '2023-06-25 18:38:30', '32', false, 2, 6),
('ORD62023162424346', '2023-12-25 20:38:30', '30', false, 2, 6),
('ORD62023162424347', '2023-11-25 19:38:30', '36', false, 2, 7),
('ORD62023162424348', '2023-06-25 20:38:30', '26', false, 2, 7);

insert into restaurant_street(street_id, restaurant_id)
values
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1),(7,1),(8,1),(9,1),
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2), (6, 2),(7,2),(8,2),(9,2),
(1, 3), (2, 3), (4, 3), (5, 3), (6, 3),(8,3),(9,3),
(1, 4), (2, 4), (4, 4), (5, 4), (6, 4),(8,4),(9,4),
(1, 5), (2, 5), (4, 5), (5, 5), (7,5),(8,5),(9,5),
(1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6),(7,6),(8,6),(9,6),
(3, 7), (4, 7), (5, 7), (7, 7),(7,7),(9,7);

insert into order_meal (quantity, order_id,price, meal_id)
values
(1, 1,'16', 1),(1, 1,'16', 2),(1, 2,'20', 4),(2, 2,'16', 5),
(1, 3,'18', 7),(1, 3,'22', 8),(1, 4,'20', 9),(2, 4,'40', 10),
(1, 5,'18', 12),(1, 5,'22', 13),(1, 6,'16', 14),(1, 6,'12', 15),
(1, 7,'16', 17),(1, 7,'16', 18),(1, 8,'16', 20),(1, 8,'8', 21),
(1, 9,'18', 22),(1, 9,'22', 23),(1, 10,'10', 25),(1, 10,'8', 26),
(1, 11,'18', 27),(1, 11,'14', 28),(1, 12,'18', 29),(1, 12,'12', 30),
(1, 13,'18', 32),(1, 13,'18', 33),(1, 14,'18', 35),(1, 14,'8', 36),
(1, 15,'16', 1),(1, 15,'16', 2),(1, 16,'20', 4),(2, 16,'16', 5),
(1, 17,'18', 7),(1, 17,'22', 8),(1, 18,'20', 9),(2, 18,'40', 10),
(1, 19,'18', 12),(1, 19,'22', 13),(1, 20,'16', 14),(1, 20,'12', 15),
(1, 21,'16', 17),(1, 21,'16', 18),(1, 22,'16', 20),(1, 22,'8', 21),
(1, 23,'18', 22),(1, 23,'22', 23),(1, 24,'10', 25),(1, 24,'8', 26),
(1, 25,'18', 27),(1, 25,'14', 28),(1, 26,'18', 29),(1, 26,'12', 30),
(1, 27,'18', 32),(1, 27,'18', 33),(1, 28,'18', 35),(1, 28,'8', 36);
