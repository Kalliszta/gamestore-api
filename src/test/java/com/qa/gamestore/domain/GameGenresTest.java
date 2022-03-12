package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameGenresTest {
	private GameGenres existingGameGenres;
	private Games game;
	private Genres genre;
	
	@BeforeEach
	void setUpForEach() {
		game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		genre = new Genres(1L, "Platformer");
		existingGameGenres = new GameGenres(1L, game, genre);
	}
	@Test
	public void testToString() {
		//given
		String expected = "GameGenres [id=1, games=Games [id=1, name=LittleBigPlanet, description=Best platformer ever, ageRating=7, cost=29.99, onlineGame=true], genres=Genres [id=1, genre=Platformer]]";
		//when
		String actual = existingGameGenres.toString();
		//then
		assertEquals(expected,actual);
	}
}
