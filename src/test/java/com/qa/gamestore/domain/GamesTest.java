package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GamesTest {
	private Games existingGame;
	
	@BeforeEach
	void setUpForEach() {
		existingGame = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGame.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetName() {
		//given
		String expected = "LittleBigPlanet";
		//when
		String actual = existingGame.getName();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetName() {
		//given
		String expected = "LittleBigPlanet2";
		//when
		existingGame.setName("LittleBigPlanet2");
		String actual = existingGame.getName();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "Games [id=1, name=LittleBigPlanet, description=Best platformer ever, ageRating=7, cost=29.99, onlineGame=true]";
		//when
		String actual = existingGame.toString();
		//then
		assertEquals(expected,actual);
	}
//	@Test
//	public void testEquals() {
//		EqualsVerifier.simple().forClass(Games.class).withPrefabValues(GamePlatforms.class, new GamePlatforms(), new GamePlatforms()).verify();
//	}
}
