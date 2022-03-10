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
	
	
	// ##### Games #####
	
	// ### Additional queries for Games ###
	
//	@Query(value = "INSERT INTO games(name,description,age_rating,cost,online_game) VALUES(:in1,:in2,:in3,:in4,:in5)", nativeQuery = true)
//	public Games insertInto(@Param("in1") String in1, @Param("in2") String in2, @Param("in3") int in3, @Param("in4") double in4, @Param("in5") boolean in5);
	
	@Query(value = "SELECT * FROM games WHERE name = :inputName", nativeQuery = true) //nativeQuery = true, enables the use of SQL commands as the value instead of using JPQL
	public List<Games> getGamesByName(@Param("inputName") String inputName);
	
	@Query(value = "SELECT * FROM games WHERE age_rating <= :inputAge", nativeQuery = true)
	public List<Games> getGamesByAge(@Param("inputAge") int inputAge);
	
	@Query(value = "SELECT * FROM games WHERE cost <= :inputCost", nativeQuery = true)
	public List<Games> getGamesByCost(@Param("inputCost") double inputCost);
	
	@Query(value = "SELECT * FROM games WHERE online_game = :inputOnline", nativeQuery = true)
	public List<Games> getGamesByOrderGame(@Param("inputOnline") boolean inputOnline);
	
	
	// ### Queries for Platforms ###
	@Query(value = "SELECT * FROM games LEFT OUTER JOIN game_platforms ON games.id=game_platforms.fk_games_id LEFT OUTER JOIN platforms ON platforms.id=game_platforms.fk_platforms_id WHERE platforms.id = :inputId", nativeQuery = true)
	public List<Games> getGamesByPlatformId(@Param("inputId") Long inputId);
	
	
	// ### Queries for Genres ###
	@Query(value = "SELECT * FROM games LEFT OUTER JOIN game_genres ON games.id=game_genres.fk_games_id LEFT OUTER JOIN genres ON genres.id=game_genres.fk_genres_id WHERE genres.id = :inputId", nativeQuery = true)
	public List<Games> getGamesByGenreId(@Param("inputId") Long inputId);
	

}
