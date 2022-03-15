package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.GameGenres;

@Repository
public interface GameGenresRepo extends JpaRepository<GameGenres, Long> {
	
}