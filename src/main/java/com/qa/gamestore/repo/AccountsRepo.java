package com.qa.gamestore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.gamestore.domain.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts, Long>{

	//TO-DO: Custom queries

}
