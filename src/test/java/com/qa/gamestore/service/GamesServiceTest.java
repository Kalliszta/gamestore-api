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

import com.qa.gamestore.domain.GameGenres;
import com.qa.gamestore.domain.GamePlatforms;
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.domain.Genres;
import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.repo.GameGenresRepo;
import com.qa.gamestore.repo.GamePlatformsRepo;
import com.qa.gamestore.repo.GamesRepo;
import com.qa.gamestore.repo.GenresRepo;
import com.qa.gamestore.repo.PlatformsRepo;

@SpringBootTest
@ActiveProfiles("test")
public class GamesServiceTest {
	private Long id;
	private Games newGame;
	private Games savedGame;
	
	@Autowired
	private GamesService service;
	
	@MockBean
	private GamesRepo repo;
	@MockBean
	private GamePlatformsRepo gPRepo;
	@MockBean
	private GameGenresRepo gGRepo;
	@MockBean
	private PlatformsRepo pRepo;
	@MockBean
	private GenresRepo gRepo;
	
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
		id = 1L;
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
		id = 1L;
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
		id = 1L;
		Optional<Games> optGame = Optional.of(new Games(1L, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true));

		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGame);
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//then
		assertThat(this.service.delete(id)).isTrue();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
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
	void testReadByGamePlatform() {
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
		Mockito.when(this.repo.getGamesByOnlineGame(online)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.readByOnlineGame(online)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByOnlineGame(online);
	}
	
	@Test
	void testReadByPlatformId() {
		//given
		//some things set up using setUpForEach
		id = 5L;
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
		id = 6L;
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
	
	@Test
	void testReadByOrderId() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		List<Games> expectedGames = Arrays.asList(
				savedGame,
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true)
				);
		
		//when
		Mockito.when(this.repo.getGamesByOrderId(id)).thenReturn(expectedGames);
		
		//then
		assertThat(this.service.items(id)).isEqualTo(expectedGames);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).getGamesByOrderId(id);
	}
	
	@Test
	void testAddPlatform() {
		//given
		Long gameId = 1L;
		Long platformId = 1L;

		
		Optional<Games> optGame = Optional.of(new Games(gameId, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true));
		Optional<Platforms> optPlatform = Optional.of( new Platforms(1L, "PS4", "PlayStation"));
		GamePlatforms newGamePlatform = new GamePlatforms(1L, 1L);
		GamePlatforms savedGamePlatform = new GamePlatforms(1L, 1L, 1L);
		
		//when
		Mockito.when(this.repo.findById(gameId)).thenReturn(optGame);
		Mockito.when(this.pRepo.findById(platformId)).thenReturn(optPlatform);
		Mockito.when(this.gPRepo.save(newGamePlatform)).thenReturn(savedGamePlatform);	
		
		//then
		assertThat(this.service.add(newGamePlatform)).isEqualTo(savedGamePlatform);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(gameId);
		Mockito.verify(this.pRepo, Mockito.times(1)).findById(platformId);
		Mockito.verify(this.gPRepo, Mockito.times(1)).save(newGamePlatform);
	}
	
	@Test
	void testAddGenre() {
		//given
		Long gameId = 1L;
		Long genreId = 1L;

		
		Optional<Games> optGame = Optional.of(new Games(gameId, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true));
		Optional<Genres> optGenre = Optional.of( new Genres(1L, "Platformer"));
		GameGenres newGameGenre = new GameGenres(1L, 1L);
		GameGenres savedGameGenre = new GameGenres(1L, 1L, 1L);
		
		//when
		Mockito.when(this.repo.findById(gameId)).thenReturn(optGame);
		Mockito.when(this.gRepo.findById(genreId)).thenReturn(optGenre);
		Mockito.when(this.gGRepo.save(newGameGenre)).thenReturn(savedGameGenre);	
		
		//then
		assertThat(this.service.add(newGameGenre)).isEqualTo(savedGameGenre);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(gameId);
		Mockito.verify(this.gRepo, Mockito.times(1)).findById(genreId);
		Mockito.verify(this.gGRepo, Mockito.times(1)).save(newGameGenre);
	}
	
}
