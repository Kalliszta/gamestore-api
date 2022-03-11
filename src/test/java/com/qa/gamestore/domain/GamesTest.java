package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import nl.jqno.equalsverifier.EqualsVerifier;

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
	public void testGetDescription() {
		//given
		String expected = "Best platformer ever";
		//when
		String actual = existingGame.getDescription();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetDescription() {
		//given
		String expected = "Best platformer and sandbox ever";
		//when
		existingGame.setDescription("Best platformer and sandbox ever");
		String actual = existingGame.getDescription();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetAgeRating() {
		//given
		Integer expected = 7;
		//when
		Integer actual = existingGame.getAgeRating();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetAgeRating() {
		//given
		Integer expected = 3;
		//when
		existingGame.setAgeRating(3);
		Integer actual = existingGame.getAgeRating();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetCost() {
		//given
		Double expected = 29.99;
		//when
		Double actual = existingGame.getCost();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetCost() {
		//given
		Double expected = 15.45;
		//when
		existingGame.setCost(15.45);
		Double actual = existingGame.getCost();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testIsOnlineGame() {
		//given
		Boolean expected = true;
		//when
		Boolean actual = existingGame.isOnlineGame();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetOnlineGame() {
		//given
		Boolean expected = false;
		//when
		existingGame.setOnlineGame(false);
		Boolean actual = existingGame.isOnlineGame();
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
//		Platforms platform = new Platforms(1L, "PS3", "PlayStation", null);
//		Genres genre = new Genres(1L, "Platformer", null);
//		Orders order = new Orders(1L, 1L);
//		EqualsVerifier.simple().forClass(Games.class)
//			.withPrefabValues(GamePlatforms.class, new GamePlatforms(1L,existingGame,platform), new GamePlatforms(1L,null,null))
//			.withPrefabValues(GameGenres.class, new GameGenres(1L,existingGame,genre), new GameGenres(1L,null,null))
//			.withPrefabValues(OrderGames.class, new OrderGames(1L,order,existingGame), new OrderGames(1L,null,null))
//			.verify();
//	}
}
