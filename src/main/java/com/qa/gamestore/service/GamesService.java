package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.GameGenres;
import com.qa.gamestore.domain.GamePlatforms;
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.domain.Genres;

import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.GameGenresRepo;
import com.qa.gamestore.repo.GamePlatformsRepo;
import com.qa.gamestore.repo.GamesRepo;

@Service
public class GamesService implements ServiceInterface<Games> {
	
	private GamesRepo repo;
	private GamePlatformsRepo pRepo;
	private GameGenresRepo gRepo;
	
	private PlatformsService pSer;
	private GenresService gSer;
	
	@Autowired
	public GamesService(GamesRepo repo, GamePlatformsRepo pRepo, GameGenresRepo gRepo, PlatformsService pSer, GenresService gSer) {
		this.repo = repo;
		this.pRepo = pRepo;
		this.gRepo = gRepo;
		this.pSer =pSer;
		this.gSer =gSer;
	}
	
	
	@Override
	public Games create(Games game) {
		return this.repo.save(game);
	}
	
	@Override
	public List<Games> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Games readById(Long id) {
		try {
			Optional<Games> opGame = this.repo.findById(id);
			if (opGame.isPresent()) {
				return opGame.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Games update(Long id, Games newGame) {
		//TO-DO exception handling
		Optional<Games> opGame = this.repo.findById(id);
		if (opGame.isPresent()) {
			Games existingGame = opGame.get();
			existingGame.updateFields(newGame);
			return this.repo.save(existingGame);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		try {
			if (repo.findById(id).isPresent()) {
				this.repo.deleteById(id);
			} else {
				throw new IdNotFoundException();
			}
		} catch (IdNotFoundException e) {
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
	
	//### custom query methods go below ###
	
	public List<Games> readByName(String name) {
		return this.repo.getGamesByName(name);
	}
	
	public List<Games> readByAge(int age) {
		return this.repo.getGamesByAge(age);
	}
	
	public List<Games> readByCost(double cost) {
		return this.repo.getGamesByCost(cost);
	}
	
	public List<Games> readByOnlineGame(boolean online) {
		return this.repo.getGamesByOnlineGame(online);
	}
	
	public List<Games> platformById(Long id) {
		return this.repo.getGamesByPlatformId(id);
	}
	
	public List<Games> genreById(Long id) {
		return this.repo.getGamesByGenreId(id);
	}
	
	public List<Games> items(Long id) {
		return this.repo.getGamesByOrderId(id);
	}
	
	public GamePlatforms add(GamePlatforms gamePlatform) {
		Games game = this.readById(gamePlatform.getGamesId());
		Platforms platform = pSer.readById(gamePlatform.getPlatformsId());
		gamePlatform.setGames(game);
		gamePlatform.setPlatforms(platform);
		return this.pRepo.save(gamePlatform);
	}
	
	public GameGenres add(GameGenres gameGenre) {
		Games game = this.readById(gameGenre.getGamesId());
		Genres genre = gSer.readById(gameGenre.getGenresId());
		gameGenre.setGames(game);
		gameGenre.setGenres(genre);
		return this.gRepo.save(gameGenre);
	}
	
}
