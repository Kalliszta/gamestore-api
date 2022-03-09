package com.qa.gamestore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	private String description;
	@NonNull
	private int ageRating;
	@NonNull
	private double cost; //TO-DO make sure to see if in money format
	private boolean onlineGame = false;
	//private double totalRating;
	//private double fkGamePlatformId;
	//private double fkGameGenreId;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "game_platforms", joinColumns = {@JoinColumn(name= "games_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "platforms_id", referencedColumnName = "id")})
	@JsonManagedReference
	@OneToMany(mappedBy= "games", fetch = FetchType.LAZY)
	private List<GamePlatforms> gamePlatforms;
	
	public void updateFields(Games newGame) {
		this.name = newGame.getName();
		this.description = newGame.getDescription();
		this.ageRating = newGame.getAgeRating();
		this.cost = newGame.getCost();
		this.onlineGame = newGame.isOnlineGame();
		//this.totalRating = newGame.getTotalRating();
		//this.fkGamePlatformId = newGame.getFkGamePlatformId();
		//this.fkGameGenreId = newGame.getFkGameGenreId();
	}
	
	
	
}
