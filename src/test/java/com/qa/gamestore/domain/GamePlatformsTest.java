package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlatformsTest {
	private GamePlatforms existingGamePlatforms;
	private Games game;
	private Platforms platform;
	
	@BeforeEach
	void setUpForEach() {
		game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		platform = new Platforms(1L, "PS4", "PlayStation");
		existingGamePlatforms = new GamePlatforms(1L, game, platform);
	}
	@Test
	public void testToString() {
		//given
		String expected = "GamePlatforms [id=1, games=Games [id=1, name=LittleBigPlanet, description=Best platformer ever, ageRating=7, cost=29.99, onlineGame=true], platforms=Platforms [id=1, name=PS4, company=PlayStation]]";
		//when
		String actual = existingGamePlatforms.toString();
		//then
		assertEquals(expected,actual);
	}
}
