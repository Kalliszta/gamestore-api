package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.AccountsRepo;

@Service
public class AccountsService implements ServiceInterface<Accounts> {
	
	private AccountsRepo repo;
	
	@Autowired
	public AccountsService(AccountsRepo repo) {
		this.repo = repo;
	}
	
	
	@Override
	public Accounts create(Accounts user) {
		//TO-DO exception handling
		return this.repo.save(user);
	}
	
	@Override
	public List<Accounts> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Accounts readById(Long id) {
		//TO-DO exception handling
		Optional<Accounts> opUser = this.repo.findById(id);
		return opUser.get();
	}

	@Override
	public Accounts update(Long id, Accounts newUser) {
		//TO-DO exception handling
		Optional<Accounts> opUser = this.repo.findById(id);
		if (opUser.isPresent()) {
			Accounts existingUser = opUser.get();
			existingUser.updateFields(newUser);
			return this.repo.save(existingUser);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
		} catch (IdNotFoundException e) {
			//TO-DO deal with more specific exceptions and log them using Loggers
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
	
}
