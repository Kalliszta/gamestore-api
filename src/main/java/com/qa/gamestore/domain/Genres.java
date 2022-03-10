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
	public class Genres {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NonNull
		private String genre;
		
		//relationship with gameGenres
		@JsonManagedReference(value = "gamesToGameGenres")
		@OneToMany(mappedBy= "genres", fetch = FetchType.LAZY)
		@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
		private List<GameGenres> gameGenres;
		
		public void updateFields(Genres newInfo) {
			this.genre = newInfo.getGenre();
		}
		
}
