DROP TABLE IF EXISTS games;
CREATE TABLE games(id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(255) NOT NULL, description VARCHAR(255), age_rating INT NOT NULL, cost DOUBLE NOT NULL, online_game BIT(1));