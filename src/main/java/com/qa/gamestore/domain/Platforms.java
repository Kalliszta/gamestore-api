package com.qa.gamestore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
		
		@JsonManagedReference
		@OneToMany(mappedBy= "platforms", fetch = FetchType.LAZY)
		private List<GamePlatforms> gamePlatforms;
		
		public void updateFields(Platforms newInfo) {
			this.name = newInfo.getName();
			this.company = newInfo.getCompany();
		}
}
