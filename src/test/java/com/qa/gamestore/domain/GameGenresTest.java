package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameGenresTest {
	private GameGenres existingGameGenres;
	
	@BeforeEach
	void setUpForEach() {
		existingGameGenres = new GameGenres(1L, 2L, 1L);
	}
	@Test
	public void testNoIdConstructor() {
		//given
		//Done using setUpForEach
		//when
		GameGenres actual = new GameGenres(2L, 1L);
		//then
		assertEquals(existingGameGenres,actual);
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGameGenres.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameId() {
		//given
		Long expected = 2L;
		//when
		Long actual = existingGameGenres.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetGameId() {
		//given
		Long expected = 2L;
		//when
		existingGameGenres.setGamesId(2L);;
		Long actual = existingGameGenres.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameIdWithExistingGame() {
		//given
		Long expected = 1L;
		Games game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		existingGameGenres.setGames(game);
		//when
		Long actual = existingGameGenres.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetGame() {
		//given
		Games expected = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		//when
		existingGameGenres.setGames(expected);
		Games actual = existingGameGenres.getGames();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGenreId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGameGenres.getGenresId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetGenreId() {
		//given
		Long expected = 1L;
		//when
		existingGameGenres.setGenresId(1L);;
		Long actual = existingGameGenres.getGenresId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGenreIdWithExistingGenre() {
		//given
		Long expected = 1L;
		Genres genre = new Genres(1L, "Platformer");
		existingGameGenres.setGenres(genre);
		//when
		Long actual = existingGameGenres.getGenresId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetGenre() {
		//given
		Genres expected = new Genres(1L, "Platformer");
		//when
		existingGameGenres.setGenres(expected);
		Genres actual = existingGameGenres.getGenres();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testUpdateFields() {
		//given
		GameGenres expected = new GameGenres(2L, 3L, 3L);
		//when
		existingGameGenres.updateFields(expected);
		//then
		assertEquals(expected,existingGameGenres);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "GameGenres [id=1, gamesId=2, genresId=1]";
		//when
		String actual = existingGameGenres.toString();
		//then
		assertEquals(expected,actual);
	}
}
