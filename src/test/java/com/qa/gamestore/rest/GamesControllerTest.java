package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.service.GamesService;

@SpringBootTest
public class GamesControllerTest {
	private Long id;
	private Games newGame;
	private Games savedGame;
	
	@Autowired
	private GamesController controller;
	
	@MockBean
	private GamesService service;
	
	
	@BeforeEach
	void setUpForEach() {
		newGame = new Games("LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		savedGame = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.service.create(newGame)).thenReturn(savedGame);
		ResponseEntity<Games> response = new ResponseEntity<Games>(savedGame, HttpStatus.CREATED);
		//then
		assertThat(response).isEqualTo(this.controller.create(newGame));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).create(newGame);
	}
	
	@Test
	void testReadAll() {
		//given
		//set up using setUpForEach
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(3L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(4L, "Horizon Forbidden West", "An RPG that takes place in the future", 16, 79.99, false),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true),
				new Games(7L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		//when
		Mockito.when(this.service.readAll()).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.get());
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}
	
	@Test
	void testReadById() {
		//given
		//set up using setUpForEach
		id = 1L;
		
		//when
		Mockito.when(this.service.readById(id)).thenReturn(savedGame);
		ResponseEntity<Games> response = new ResponseEntity<Games>(savedGame, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.get(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readById(id);
	}
	
}
