package com.qa.gamestore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
		
		@OneToOne(mappedBy = "platforms")
		private Games games;
		
		public void updateFields(Platforms newInfo) {
			this.name = newInfo.getName();
			this.company = newInfo.getCompany();
		}
}
