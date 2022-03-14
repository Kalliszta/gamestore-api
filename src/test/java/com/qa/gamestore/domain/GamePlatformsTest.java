package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlatformsTest {
	private GamePlatforms existingGamePlatforms;
	
	@BeforeEach
	void setUpForEach() {
		existingGamePlatforms = new GamePlatforms(1L, 2L, 1L);
	}
	@Test
	public void testNoIdConstructor() {
		//given
		//Done using setUpForEach
		//when
		GamePlatforms actual = new GamePlatforms(2L, 1L);
		//then
		assertEquals(existingGamePlatforms,actual);
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGamePlatforms.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameId() {
		//given
		Long expected = 2L;
		//when
		Long actual = existingGamePlatforms.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetGameId() {
		//given
		Long expected = 2L;
		//when
		existingGamePlatforms.setGamesId(2L);;
		Long actual = existingGamePlatforms.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameIdWithExistingGame() {
		//given
		Long expected = 1L;
		Games game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		existingGamePlatforms.setGames(game);
		//when
		Long actual = existingGamePlatforms.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetGame() {
		//given
		Games expected = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		//when
		existingGamePlatforms.setGames(expected);
		Games actual = existingGamePlatforms.getGames();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetPlatformId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGamePlatforms.getPlatformsId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetPlatformId() {
		//given
		Long expected = 1L;
		//when
		existingGamePlatforms.setPlatformsId(1L);;
		Long actual = existingGamePlatforms.getPlatformsId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetPlatformIdWithExistingPlatform() {
		//given
		Long expected = 1L;
		Platforms platform = new Platforms(1L, "PS4", "PlayStation");
		existingGamePlatforms.setPlatforms(platform);
		//when
		Long actual = existingGamePlatforms.getPlatformsId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetPlatform() {
		//given
		Platforms expected = new Platforms(1L, "PS4", "PlayStation");
		//when
		existingGamePlatforms.setPlatforms(expected);
		Platforms actual = existingGamePlatforms.getPlatforms();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testUpdateFields() {
		//given
		GamePlatforms expected = new GamePlatforms(2L, 3L, 3L);
		//when
		existingGamePlatforms.updateFields(expected);
		//then
		assertEquals(expected,existingGamePlatforms);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "GamePlatforms [id=1, gamesId=2, platformsId=1]";
		//when
		String actual = existingGamePlatforms.toString();
		//then
		assertEquals(expected,actual);
	}
}
