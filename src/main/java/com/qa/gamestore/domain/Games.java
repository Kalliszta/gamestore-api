package com.qa.gamestore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


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

	@JsonManagedReference
	@OneToMany(mappedBy= "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GamePlatforms> gamePlatforms;
	
	@JsonManagedReference
	@OneToMany(mappedBy= "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GameGenres> gameGenres;
	
	//same for genre
	
	public void updateFields(Games newGame) {
		this.name = newGame.getName();
		this.description = newGame.getDescription();
		this.ageRating = newGame.getAgeRating();
		this.cost = newGame.getCost();
		this.onlineGame = newGame.isOnlineGame();
		//this.totalRating = newGame.getTotalRating();
	}
	
	
	
}
