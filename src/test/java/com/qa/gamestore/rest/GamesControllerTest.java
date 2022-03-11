package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.service.GamesService;

@SpringBootTest
public class GamesControllerTest {
	private Games newGame;
	private Games savedGame;
	
	@MockBean
	private GamesService service;
	
	@Autowired
	private GamesController controller;
	
	@BeforeEach
	void setUpForEach() {
		newGame = new Games("LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		savedGame = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
	}
	
//	@Test
//	void testCreate() {
//		//given
//		//set up using setUpForEach
//		
//		//when
//		Mockito.when(this.service.create(newGame)).thenReturn(savedGame);
//		
//		//then
//		assertThat(this.controller.create(newGame)).isEqualTo(savedGame);
//		
//		//verify
//		Mockito.verify(this.service, Mockito.times(1)).create(newGame);
//		Mockito.verify(this.service, Mockito.times(1)).create(newGame);
//	}
	
}
