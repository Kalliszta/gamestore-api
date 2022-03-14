package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.PlatformsRepo;

@Service
public class PlatformsService implements ServiceInterface<Platforms>{

	private PlatformsRepo repo;
	
	@Autowired
	public PlatformsService(PlatformsRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Platforms create(Platforms platform) {
		return this.repo.save(platform);
	}
	
	@Override
	public List<Platforms> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Platforms readById(Long id) {
		try {
			Optional<Platforms> opPlatform = this.repo.findById(id);
			if (opPlatform.isPresent()) {
				return opPlatform.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Platforms update(Long id, Platforms newPlatform) {
		//TO-DO exception handling
		Optional<Platforms> opPlatform = this.repo.findById(id);
		if (opPlatform.isPresent()) {
			Platforms existingPlatform = opPlatform.get();
			existingPlatform.updateFields(newPlatform);
			return this.repo.save(existingPlatform);
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
