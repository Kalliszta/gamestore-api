package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.gamestore.domain.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts, Long>{
	
	//TO-DO: Custom queries

}
