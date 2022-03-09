package com.qa.gamestore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Games;

@Repository
public interface GamesRepo extends JpaRepository<Games, Long> {
	
	//TO-DO: Custom queries
	@Query(value = "SELECT * FROM games LEFT OUTER JOIN game_platforms ON games.id=game_platforms.fk_games_id LEFT OUTER JOIN platforms ON platforms.id=game_platforms.fk_platforms_id WHERE platforms.id = :inputId", nativeQuery = true)
	public List<Games> getGamesWithPlatformId(@Param("inputId") Long inputId);
}
