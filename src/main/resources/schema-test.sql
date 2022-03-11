DROP TABLE IF EXISTS game_platforms;
DROP TABLE IF EXISTS game_genres;
DROP TABLE IF EXISTS order_games;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS platforms;
DROP TABLE IF EXISTS genres;
CREATE TABLE games(id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(255) NOT NULL, description VARCHAR(255), age_rating INT NOT NULL, cost DOUBLE NOT NULL, online_game BIT(1));
CREATE TABLE platforms(id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(255) NOT NULL, company VARCHAR(255));
CREATE TABLE genres(id BIGINT GENERATED BY DEFAULT AS IDENTITY, genre VARCHAR(255) NOT NULL);
CREATE TABLE game_platforms(id BIGINT GENERATED BY DEFAULT AS IDENTITY, fk_games_id VARCHAR(255) NOT NULL, fk_platforms_id VARCHAR(255) NOT NULL, FOREIGN KEY (fk_games_id) REFERENCES games (id) ON DELETE CASCADE, FOREIGN KEY (fk_platforms_id) REFERENCES platforms (id) ON DELETE CASCADE);
CREATE TABLE game_genres(id BIGINT GENERATED BY DEFAULT AS IDENTITY, fk_games_id VARCHAR(255) NOT NULL, fk_genres_id VARCHAR(255) NOT NULL, FOREIGN KEY (fk_games_id) REFERENCES games (id) ON DELETE CASCADE, FOREIGN KEY (fk_genres_id) REFERENCES genres (id) ON DELETE CASCADE);