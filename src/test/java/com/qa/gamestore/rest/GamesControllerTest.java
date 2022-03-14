package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

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
	
	@Test
	void testUpdate() {
		//given
		//set up using setUpForEach
		id = 1L;
		Games updateTo = new Games(id, "LittleBigPlanet2", "The second LBP game", 3, 35.67, false); //each information is changed to see if all are changed

		//when
		
		Mockito.when(this.service.update(id, updateTo)).thenReturn(updateTo);
		ResponseEntity<Games> response = new ResponseEntity<Games>(updateTo, HttpStatus.ACCEPTED);
		
		//then
		assertThat(response).isEqualTo(this.controller.update(id, updateTo));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).update(id, updateTo);
	}
	
	@Test
	void testDelete() {
		//given
		id = 1L;
		
		//when
		Mockito.when(this.service.delete(id)).thenReturn(true);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		//then
		assertThat(response).isEqualTo(this.controller.delete(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).delete(id);
	}
	
	@Test
	void testReadByName() {
		//given
		//set up using setUpForEach
		String name = "Elder Scrolls";
		
		List<Games> expectedGames = Arrays.asList(
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(7L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		
		//when
		Mockito.when(this.service.readByName(name)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByName(name));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readByName(name);
	}
	
	@Test
	void testReadByAge() {
		//given
		//set up using setUpForEach
		Integer age = 7;
		
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		//when
		Mockito.when(this.service.readByAge(age)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByAge(age));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readByAge(age);
	}
	
	@Test
	void testReadByCost() {
		//given
		//set up using setUpForEach
		Double cost = 32.65;
		
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(3L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(7L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		
		//when
		Mockito.when(this.service.readByCost(cost)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByCost(cost));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readByCost(cost);
	}
	
	@Test
	void testReadByOrderGame() {
		//given
		//set up using setUpForEach
		Boolean online = true;
		
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		//when
		Mockito.when(this.service.readByOnlineGame(online)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByOnlineGame(online));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readByOnlineGame(online);
	}
	
	@Test
	void testReadByPlatformId() {
		//given
		//set up using setUpForEach
		id = 5L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(1L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(2L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		
		//when
		Mockito.when(this.service.platformById(id)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByPlatform(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).platformById(id);
	}
	
	@Test
	void testReadByGenreId() {
		//given
		//set up using setUpForEach
		id = 6L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(5L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		//when
		Mockito.when(this.service.genreById(id)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.getByGenre(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).genreById(id);
	}
	
	@Test
	void testReadByOrderId() {
		//given
		//set up using setUpForEach
		id = 1L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true)
				);
		
		//when
		Mockito.when(this.service.items(id)).thenReturn(expectedGames);
		ResponseEntity<List<Games>> response = new ResponseEntity<List<Games>>(expectedGames, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.items(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).items(id);
	}

}
