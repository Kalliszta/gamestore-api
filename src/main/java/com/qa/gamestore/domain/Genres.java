package com.qa.gamestore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public class Genres {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NonNull
		private String genre;
		
		public void updateFields(Platforms newInfo) {
			this.genre = newInfo.getName();
		}
}
