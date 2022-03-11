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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


	@Entity
	@NoArgsConstructor
	public class Genres {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NonNull
		private String genre;
		
		public Genres(@NonNull String genre) {
			super();
			this.genre = genre;
		}

		public Genres(Long id, @NonNull String genre) {
			super();
			this.id = id;
			this.genre = genre;
		}
		
		//relationship with gameGenres
		@JsonManagedReference
		@OneToMany(mappedBy= "genres", fetch = FetchType.LAZY)
		@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
		private List<GameGenres> gameGenres;
		
		public void updateFields(Genres newInfo) {
			this.genre = newInfo.getGenre();
		}

		public Long getId() {
			return id;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		@Override
		public String toString() {
			return "Genres [id=" + id + ", genre=" + genre + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(genre);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Genres other = (Genres) obj;
			return Objects.equals(genre, other.genre);
		}
		
		
		
}
