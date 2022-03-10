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
	public class Platforms {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NonNull
		private String name;
		private String company;
		
		//relationship with gamePlatforms
		@JsonManagedReference
		@OneToMany(mappedBy= "platforms", fetch = FetchType.LAZY)
		@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
		private List<GamePlatforms> gamePlatforms;
		
		public void updateFields(Platforms newInfo) {
			this.name = newInfo.getName();
			this.company = newInfo.getCompany();
		}
}
