package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Genres;

@Repository
public interface GenresRepo extends JpaRepository<Genres, Long> {
	
}