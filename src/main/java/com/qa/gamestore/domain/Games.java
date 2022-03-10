package com.qa.gamestore.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
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
	private int ageRating;
	@NonNull //TO-DO fix to make NonNull
	private double cost; //TO-DO make sure to see if in money format
	private boolean onlineGame = false;
	//private double totalRating;

	
	//relationship with gamePlatforms
	//@JsonManagedReference(value = "gamesToGamePlatforms")
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GamePlatforms> gamePlatforms;

//	//relationship with gameGenres
//	@JsonManagedReference(value = "gamesToGameGenres")
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<GameGenres> gameGenres;
	
//	//relationship with orderGames
//	@Transient
//	@JsonManagedReference(value = "gamesToOrderGames")
	@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<OrderGames> orderGames;
	
	
	public Games(Long id, String name, String description, int ageRating, double cost, boolean onlineGame) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ageRating = ageRating;
		this.cost = cost;
		this.onlineGame = onlineGame;
	}


	public Games(String name, String description, int ageRating, double cost, boolean onlineGame) {
		super();
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



	public int getAgeRating() {
		return ageRating;
	}



	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public boolean isOnlineGame() {
		return onlineGame;
	}



	public void setOnlineGame(boolean onlineGame) {
		this.onlineGame = onlineGame;
	}



	@Override
	public String toString() {
		return "Games [id=" + id + ", name=" + name + ", description=" + description + ", ageRating=" + ageRating
				+ ", cost=" + cost + ", onlineGame=" + onlineGame + "]";
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
		return ageRating == other.ageRating && Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost) && Objects.equals(description, other.description) && Objects.equals(name, other.name) && onlineGame == other.onlineGame;
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
