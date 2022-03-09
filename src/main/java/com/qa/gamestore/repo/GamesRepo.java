package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Games;

@Repository
public interface GamesRepo extends JpaRepository<Games, Long>{
	
	//TO-DO: Custom queries

}
