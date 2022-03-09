package com.qa.gamestore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
}
