package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Platforms;

@Repository
public interface PlatformsRepo extends JpaRepository<Platforms, Long> {
	
}