package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderGamesTest {
	private OrderGames existingOrderGames;
	private Accounts account;
	private Orders order;
	private Games game;
	
	@BeforeEach
	void setUpForEach() {
		account = new Accounts(1L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
		order = new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		existingOrderGames = new OrderGames(1L, order, game);
	}
	@Test
	public void testToString() {
		//given
		String expected = "OrderGames [id=1, orders=Orders [id=1, accountsId=1, orderDate=2022-03-12 13:12:18.0], games=Games [id=1, name=LittleBigPlanet, description=Best platformer ever, ageRating=7, cost=29.99, onlineGame=true]]";
		//when
		String actual = existingOrderGames.toString();
		//then
		assertEquals(expected,actual);
	}
}
