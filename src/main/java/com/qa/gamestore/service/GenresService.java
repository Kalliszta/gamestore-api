package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Genres;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.GenresRepo;

@Service
public class GenresService implements ServiceInterface<Genres>{

	private GenresRepo repo;
	
	@Autowired
	public GenresService(GenresRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Genres create(Genres genre) {
		return this.repo.save(genre);
	}
	
	@Override
	public List<Genres> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Genres readById(Long id) {
		try {
			Optional<Genres> opGenre = this.repo.findById(id);
			if (opGenre.isPresent()) {
				return opGenre.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Genres update(Long id, Genres newGenre) {
		//TO-DO exception handling
		Optional<Genres> opGenre = this.repo.findById(id);
		if (opGenre.isPresent()) {
			Genres existingGenre = opGenre.get();
			existingGenre.updateFields(newGenre);
			return this.repo.save(existingGenre);
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
	
}
