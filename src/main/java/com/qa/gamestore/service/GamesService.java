package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.GamePlatforms;
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.repo.GamesRepo;

@Service
public class GamesService implements ServiceInterface<Games> {
	
	private GamesRepo repo;
	
	@Autowired
	public GamesService(GamesRepo repo) {
		this.repo = repo;
	}
	
	
	@Override
	public Games create(Games game) {
		//TO-DO exception handling
		return this.repo.save(game);
	}
	@Override
	public List<Games> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Games readById(Long id) {
		//TO-DO exception handling
		Optional<Games> opGame = this.repo.findById(id);
		return opGame.get();
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
			this.repo.deleteById(id);
		} catch (Exception e) {
			//TO-DO deal with more specific exceptions and log them using Loggers
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
	
	//### custom query methods go below ###
	
	public List<Games> readByName(String name) {
		return this.repo.getGamesByName(name);
	}
	
	public List<Games> readByOrderGame(boolean online) {
		return this.repo.getGamesByOrderGame(online);
	}
	
	public List<Games> platformById(Long id) {
		return this.repo.getGamesByPlatformId(id);
	}
	
	public List<Games> genreById(Long id) {
		return this.repo.getGamesByGenreId(id);
	}
}
