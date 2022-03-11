package com.qa.gamestore.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@NoArgsConstructor
public class Games {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	private String description;
	@NonNull //TO-DO fix to make NonNull
	private Integer ageRating;
	@NonNull //TO-DO fix to make NonNull
	private Double cost; //TO-DO make sure to see if in money format
	private Boolean onlineGame = false;
	//private double totalRating;

	
	//relationship with gamePlatforms
	//@JsonManagedReference(value = "gamesToGamePlatforms")
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GamePlatforms> gamePlatforms;

	//relationship with gameGenres
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GameGenres> gameGenres;
	
	//relationship with orderGames
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<OrderGames> orderGames;
	
	public Games(String name, String description, Integer ageRating, Double cost, Boolean onlineGame) {
		this.name = name;
		this.description = description;
		this.ageRating = ageRating;
		this.cost = cost;
		this.onlineGame = onlineGame;
	}
	
	public Games(Long id, String name, String description, Integer ageRating, Double cost, Boolean onlineGame) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ageRating = ageRating;
		this.cost = cost;
		this.onlineGame = onlineGame;
	}
	
	public void updateFields(Games newGame) {
		this.name = newGame.getName();
		this.description = newGame.getDescription();
		this.ageRating = newGame.getAgeRating();
		this.cost = newGame.getCost();
		this.onlineGame = newGame.isOnlineGame();
		//this.totalRating = newGame.getTotalRating();
	}


	public Long getId() {
		return id;
	}	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getAgeRating() {
		return ageRating;
	}



	public void setAgeRating(Integer ageRating) {
		this.ageRating = ageRating;
	}



	public Double getCost() {
		return cost;
	}



	public void setCost(Double cost) {
		this.cost = cost;
	}



	public Boolean isOnlineGame() {
		return onlineGame;
	}



	public void setOnlineGame(Boolean onlineGame) {
		this.onlineGame = onlineGame;
	}



	@Override
	public String toString() {
		return "Games [id=" + id + ", name=" + name + ", description=" + description + ", ageRating=" + ageRating + ", cost=" + cost + ", onlineGame=" + onlineGame + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ageRating, cost, description, name, onlineGame);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Games other = (Games) obj;
		return Objects.equals(ageRating, other.ageRating) 
				&& Objects.equals(cost, other.cost)
				&& Objects.equals(description, other.description)
				&& Objects.equals(name, other.name)
				&& Objects.equals(onlineGame, other.onlineGame);
	}



//	public List<GamePlatforms> getGamePlatforms() {
//		return gamePlatforms;
//	}
//
//
//	public List<GameGenres> getGameGenres() {
//		return gameGenres;
//	}
//
//
//	public List<OrderGames> getOrderGames() {
//		return orderGames;
//	}


	
}
