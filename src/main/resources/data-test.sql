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