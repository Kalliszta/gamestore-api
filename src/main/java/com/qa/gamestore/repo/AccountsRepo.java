package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Accounts;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Long>{
	
	//TO-DO: Custom queries

}
