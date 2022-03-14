package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.exceptions.UsernameAlreadyExistsException;
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
		try {
			if (repo.getByUsername(user.getUsername()).isEmpty()) {
				return this.repo.save(user);
			} else {
				throw new UsernameAlreadyExistsException();
			}
		} catch(UsernameAlreadyExistsException userExists) {
			//if have time save error message temporarily in a logger through doing LOGGER.debug(userExists.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Accounts> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Accounts readById(Long id) {
		Optional<Accounts> opUser = this.repo.findById(id);
		if (opUser.isPresent()) {
			return opUser.get();
		} else {
			return null;
		}
	}

	@Override
	public Accounts update(Long id, Accounts newUser) {
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
