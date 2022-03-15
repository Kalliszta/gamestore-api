package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderGamesTest {
	private OrderGames existingOrderGame;
	
	@BeforeEach
	void setUpForEach() {
		existingOrderGame = new OrderGames(1L, 1L, 1L);
	}
	
	@Test
	public void testNoIdConstructor() {
		//given
		//Done using setUpForEach
		//when
		OrderGames actual = new OrderGames(1L, 1L);
		//then
		assertEquals(existingOrderGame,actual);
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingOrderGame.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetOrderId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingOrderGame.getOrdersId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetOrderId() {
		//given
		Long expected = 1L;
		//when
		existingOrderGame.setOrdersId(1L);;
		Long actual = existingOrderGame.getOrdersId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetOrderIdWithExistingOrder() {
		//given
		Long expected = 1L;
		Orders order = new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		existingOrderGame.setOrders(order);
		//when
		Long actual = existingOrderGame.getOrdersId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetOrder() {
		//given
		Orders expected = new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		//when
		existingOrderGame.setOrders(expected);
		Orders actual = existingOrderGame.getOrders();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingOrderGame.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetGameId() {
		//given
		Long expected = 1L;
		//when
		existingOrderGame.setGamesId(1L);;
		Long actual = existingOrderGame.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGameIdWithExistingGame() {
		//given
		Long expected = 1L;
		Games game = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		existingOrderGame.setGames(game);
		//when
		Long actual = existingOrderGame.getGamesId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSetGame() {
		//given
		Games expected = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		//when
		existingOrderGame.setGames(expected);
		Games actual = existingOrderGame.getGames();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testUpdateFields() {
		//given
		OrderGames expected = new OrderGames(1L, 2L, 3L);
		//when
		existingOrderGame.updateFields(expected);
		//then
		assertEquals(expected,existingOrderGame);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "OrderGames [id=1, ordersId=1, gamesId=1]";
		//when
		String actual = existingOrderGame.toString();
		//then
		assertEquals(expected,actual);
	}
}
