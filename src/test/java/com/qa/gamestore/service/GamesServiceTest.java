package com.qa.gamestore.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.repo.GamesRepo;

@SpringBootTest
public class GamesServiceTest {
	private Games newGame;
	private Games savedGame;
	
	@Autowired
	private GamesService service;
	
	@MockBean
	private GamesRepo repo;
	
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
		Mockito.when(this.repo.save(newGame)).thenReturn(savedGame);
		
		//then
		assertThat(this.service.create(newGame)).isEqualTo(savedGame);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newGame);
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
}
