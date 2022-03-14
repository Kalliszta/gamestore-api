package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.OrderGames;

@Repository
public interface OrderGamesRepo extends JpaRepository<OrderGames, Long> {
	
}