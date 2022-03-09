package com.qa.gamestore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

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
	private boolean online;
	//private double totalRating;
	//private double fkGamePlatformId;
	//private double fkGameGenreId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "game_platforms", joinColumns = {@JoinColumn(name= "games_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "platforms_id", referencedColumnName = "id")})
	private Platforms platforms;
	
	public void updateFields(Games newGame) {
		this.name = newGame.getName();
		this.description = newGame.getDescription();
		this.ageRating = newGame.getAgeRating();
		this.cost = newGame.getCost();
		this.online = newGame.isOnline();
		//this.totalRating = newGame.getTotalRating();
		//this.fkGamePlatformId = newGame.getFkGamePlatformId();
		//this.fkGameGenreId = newGame.getFkGameGenreId();
	}
	
	
	
}
