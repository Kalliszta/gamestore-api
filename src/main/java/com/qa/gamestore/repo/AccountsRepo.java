package com.qa.gamestore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Accounts;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Long>{
	
	// ### Additional queries for Accounts ###
	@Query(value = "SELECT * FROM accounts WHERE username = :inputUsername", nativeQuery = true) // nativeQuery = true, enables the use of SQL commands as the value instead of using JPQL
	public List<Accounts> getByUsername(@Param("inputUsername") String inputUsername);

}
