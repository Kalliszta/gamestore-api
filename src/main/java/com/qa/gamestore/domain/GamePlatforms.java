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
	public class GamePlatforms {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//relationship with games
		@JsonBackReference(value = "games")
		@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_games_id")
		private Games games;
		
		//relationship with platforms
		@JsonBackReference(value = "platforms")
		@ManyToOne(targetEntity = Platforms.class, fetch = FetchType.LAZY)
		@JoinColumn(name="fk_platforms_id")
		private Platforms platforms;

		@NonNull
		@Transient
		private Long gamesId;
		
		@NonNull
		@Transient
		private Long platformsId;
		
		public GamePlatforms(Long gamesId, Long platformsId) {
			this.gamesId = gamesId;
			this.platformsId = platformsId;
		}
		
		public GamePlatforms(Long id, Long gamesId, Long platformsId) {
			this.id = id;
			this.gamesId = gamesId;
			this.platformsId = platformsId;
		}
		
		public void updateFields(GamePlatforms newGamePlatforms) {
			this.gamesId = newGamePlatforms.getGamesId();
			this.platformsId = newGamePlatforms.getPlatformsId();
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

		public Long getPlatformsId() {
			if (platforms != null) {
				return this.platforms.getId();
			} else {
				return platformsId;
			}
		}

		public void setPlatformsId(Long platformsId) {
			this.platformsId = platformsId;
		}
		
		public Games getGames() {
			return games;
		}

		public void setGames(Games games) {
			this.games = games;
		}

		public Platforms getPlatforms() {
			return platforms;
		}

		public void setPlatforms(Platforms platforms) {
			this.platforms = platforms;
		}

		@Override
		public String toString() {
			return "GamePlatforms [id=" + id + ", gamesId=" + gamesId + ", platformsId=" + platformsId + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(gamesId, platformsId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GamePlatforms other = (GamePlatforms) obj;
			return Objects.equals(gamesId, other.gamesId)
					&& Objects.equals(platformsId, other.platformsId);
		}
		
}
