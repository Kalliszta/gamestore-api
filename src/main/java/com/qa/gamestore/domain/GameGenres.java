package com.qa.gamestore.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;
import lombok.NonNull;

	@Entity
	@NoArgsConstructor
	public class GameGenres {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//relationship with games
		@JsonBackReference(value = "games")
		@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_games_id")
		private Games games;
		
		//relationship with genres
		@JsonBackReference(value = "genres")
		@ManyToOne(targetEntity = Genres.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_genres_id")
		private Genres genres;

		@NonNull
		@Transient
		private Long gamesId;
		
		@NonNull
		@Transient
		private Long genresId;
		
		public GameGenres(Long gamesId, Long genresId) {
			this.gamesId = gamesId;
			this.genresId = genresId;
		}
		
		public GameGenres(Long id, Long gamesId, Long genresId) {
			this.id = id;
			this.gamesId = gamesId;
			this.genresId = genresId;
		}
		
		public void updateFields(GameGenres newGameGenres) {
			this.gamesId = newGameGenres.getGamesId();
			this.genresId = newGameGenres.getGenresId();
		}
		
		public Long getId() {
			return id;
		}
		
		public Long getGamesId() {
			if (games != null) {
				return this.games.getId();
			} else {
				return gamesId;
			}
		}

		public void setGamesId(Long gamesId) {
			this.gamesId = gamesId;
		}

		public Long getGenresId() {
			if (genres != null) {
				return this.genres.getId();
			} else {
				return genresId;
			}
		}

		public void setGenressId(Long genresId) {
			this.genresId = genresId;
		}
		
		public Games getGames() {
			return games;
		}

		public void setGames(Games games) {
			this.games = games;
		}

		public Genres getGenres() {
			return genres;
		}

		public void setGenres(Genres genres) {
			this.genres = genres;
		}

		@Override
		public String toString() {
			return "GameGenres [id=" + id + ", gamesId=" + gamesId + ", genresId=" + genresId + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(gamesId, genresId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GameGenres other = (GameGenres) obj;
			return Objects.equals(gamesId, other.gamesId)
					&& Objects.equals(genresId, other.genresId);
		}

		
}
