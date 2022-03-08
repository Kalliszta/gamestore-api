package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.repo.AccountsRepo;

@Service
public class AccountsService implements ServiceInterface<Accounts> {
	
	private AccountsRepo repo;
	
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
		} catch (Exception e) {
			//TO-DO deal with more specific exceptions and log them using Loggers
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
//	public boolean validateInput() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String inUsername = auth.getName();
//		Object inPassword = auth.getCredentials();
//		List<Accounts> all = this.repo.findAll();
//		System.out.println(inUsername);
//		//System.out.println(inPassword);
//		UserDetails userDetails = (UserDetails) auth.getPrincipal();
//		//System.out.println("User has authorities: " + userDetails.getAuthorities());
//		for (Accounts account: all) {
//			if (inUsername.equals(account.getUsername()) && inPassword.equals(account.getPassword())) {
//				System.out.println("user found");
//				return true;
//			}
//		}
//		return false;
//		
//	}
	
	
}
