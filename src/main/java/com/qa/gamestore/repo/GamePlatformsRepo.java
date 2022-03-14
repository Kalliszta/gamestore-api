package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.GamePlatforms;

@Repository
public interface GamePlatformsRepo extends JpaRepository<GamePlatforms, Long> {
	
}