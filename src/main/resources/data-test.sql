INSERT INTO games(name, description, age_rating, cost, online_game) VALUES
('Elder Scrolls', 'An RPG', 18, 15.99, 1),
('Horizon Zero Dawn', 'An RPG that takes place in the future', 16, 29.99, 0),
('Horizon Forbidden West', 'An RPG that takes place in the future', 16, 79.99, 0),
('Minecraft', 'A fun game to play with friends', 7, 19.99, 1),
('Animal Crossing New Horizons', 'The most relaxing game ever', 3, 45.25, 1),
('Elder Scrolls', 'Skyrim', 18, 32.65, 0);

INSERT INTO platforms(name, company) VALUES
('PC','Microsoft'),
('PC','Steam'),
('PC','Epic Games'),
('PS3','PlayStation'),
('PS4','PlayStation'),
('PS5','PlayStation'),
('Nintendo Switch','Nintendo')
;

INSERT INTO game_platforms(fk_games_id,fk_platforms_id) VALUES
(1,2),
(1,5),
(1,6),
(2,3),
(2,5),
(3,6),
(4,1),
(4,4),
(4,5),
(4,7),
(5,7),
(6,2),
(6,5)
;

INSERT INTO genres(genre) VALUES
('Action'),
('Adventure'),
('MMORPG'),
('Platformer'),
('RPG'),
('Sandbox'),
('Shooter')
;

INSERT INTO game_genres(fk_games_id,fk_genres_id) VALUES
(1,1),
(1,2),
(1,3),
(1,5),
(2,2),
(2,5),
(3,2),
(3,5),
(4,2),
(4,6),
(5,6),
(6,1),
(6,2),
(6,5)
;

INSERT INTO accounts(username, password, firstname, surname, age, email, phone_number, admin) VALUES
('KallisztaG','password123','Kalliszta','Grof', 19, 'kalg@email.com', '07474354663', 1),
('LilyHere','pass1','Lily','Smith', 25, 'lily@email.com', '05354664637', 0),
('User3','pAsSwOrD','Bob','Roberts', 12, 'roberts@email.com', '07853364637', 0),
('Steph','&7C,Mt67@)skZO3','Steph','Ann', 30, 'stepha@email.com', '07853388831', 0)
;

INSERT INTO orders(order_date, fk_accounts_id) VALUES
('2022-03-12 13:08:45.000000', 1),
('2022-03-11 08:56:32.000000', 1),
('2022-03-12 07:00:12.000000', 2),
('2022-03-11 20:09:58.000000', 2),
('2022-03-12 07:00:12.000000', 3)
;

INSERT INTO order_games(fk_orders_id, fk_games_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 1),
(3, 5)
;
