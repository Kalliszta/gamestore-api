package com.qa.gamestore.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	public class GameGenres {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//relationship with games
		@JsonBackReference
		@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_games_id")
		private Games games;
		
		//relationship with genres
		@JsonBackReference
		@ManyToOne(targetEntity = Genres.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_genres_id")
		private Genres genres;

		@Override
		public String toString() {
			return "GameGenres [id=" + id + ", games=" + games + ", genres=" + genres + "]";
		}

		
}
