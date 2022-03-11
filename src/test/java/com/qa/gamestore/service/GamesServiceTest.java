package com.qa.gamestore.service;

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
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.repo.GamesRepo;

@SpringBootTest
@ActiveProfiles("test")
public class GamesServiceTest {
	private Games newGame;
	private Games savedGame;
	
	@Autowired
	private GamesService service;
	
	@MockBean
	private GamesRepo repo;
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used
		newGame = new Games("LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
		savedGame = new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true);
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.repo.save(newGame)).thenReturn(savedGame);
		
		//then
		assertThat(this.service.create(newGame)).isEqualTo(savedGame);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newGame);
	}
	
	@Test
	void testReadAll() {
		//given
		//some things set up using setUpForEach
	
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
		Mockito.when(this.repo.findAll()).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readAll()).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testReadById() {
		//given
		//some things set up using setUpForEach
		Long id = 1L;
		Optional<Games> optGame = Optional.of(new Games(id, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGame);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedGame);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		Long id = 1L;
		Optional<Games> optGame = Optional.of(new Games(id, null, null, 0, 0.0, null));
		Games updatedGame = new Games(id, newGame.getName(), newGame.getDescription(), newGame.getAgeRating(), newGame.getCost(), newGame.isOnlineGame());
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGame);
		Mockito.when(this.repo.save(updatedGame)).thenReturn(updatedGame);
		
		//then
		assertThat(this.service.update(id, newGame)).isEqualTo(updatedGame);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedGame);
	}
	
	@Test
	void testDelete() {
		//given
		//some things set up using setUpForEach
		Long id = 1L;

		//when
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//then
		assertThat(this.service.delete(id)).isTrue();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	void testDeleteFail() {
		//given
		//some things set up using setUpForEach
		Long id = 100L;

		//when
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		
		//then
		assertThat(this.service.delete(id)).isFalse();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	// ### Tests for additional methods ###
	@Test
	void testReadByName() {
		//given
		//some things set up using setUpForEach
		String name = "Elder Scrolls";
		List<Games> expectedGames = Arrays.asList(
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(7L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		
		//when
		Mockito.when(this.repo.getGamesByName(name)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readByName(name)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByName(name);
	}
	
	@Test
	void testReadByAge() {
		//given
		//some things set up using setUpForEach
		Integer age = 16;
		List<Games> expectedGames = Arrays.asList(
				new Games(3L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(4L, "Horizon Forbidden West", "An RPG that takes place in the future", 16, 79.99, false),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		//when
		Mockito.when(this.repo.getGamesByAge(age)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readByAge(age)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByAge(age);
	}
	
	@Test
	void testReadByCost() {
		//given
		//some things set up using setUpForEach
		Double cost = 29.99;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(3L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true)
				);
		
		//when
		Mockito.when(this.repo.getGamesByCost(cost)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readByCost(cost)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByCost(cost);
	}
	
	@Test
	void testReadByOrderGame() {
		//given
		//some things set up using setUpForEach
		Boolean online = true;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(2L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(5L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		//when
		Mockito.when(this.repo.getGamesByOrderGame(online)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readByOrderGame(online)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByOrderGame(online);
	}
	
	@Test
	void testReadByPlatformId() {
		//given
		//some things set up using setUpForEach
		Long id = 5L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(1L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(2L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(6L, "Elder Scrolls", "Skyrim", 18, 32.65, false)
				);
		
		//when
		Mockito.when(this.repo.getGamesByPlatformId(id)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.platformById(id)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByPlatformId(id);
	}
	
	@Test
	void testReadByGenreId() {
		//given
		//some things set up using setUpForEach
		Long id = 6L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(5L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		//when
		Mockito.when(this.repo.getGamesByGenreId(id)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.genreById(id)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByGenreId(id);
	}
	
}
