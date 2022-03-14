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

import lombok.NoArgsConstructor;
import lombok.NonNull;


	@Entity
	@NoArgsConstructor
	public class Platforms {
		
		public Platforms(String name, String company) {
			this.name = name;
			this.company = company;
		}
		
		public Platforms(Long id, String name, String company) {
			this.id = id;
			this.name = name;
			this.company = company;
		}
		
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

		public Long getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		@Override
		public String toString() {
			return "Platforms [id=" + id + ", name=" + name + ", company=" + company + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(company, name);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Platforms other = (Platforms) obj;
			return Objects.equals(company, other.company) && Objects.equals(name, other.name);
		}	

}
