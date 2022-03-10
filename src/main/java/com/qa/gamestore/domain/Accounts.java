package com.qa.gamestore.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@NoArgsConstructor
public class Accounts {
	
	static String hashCode = "udx";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NonNull
	private String username;
	
	@NonNull
	private String password;
	
	private String firstname;
	private String surname;
	
	@NonNull
	private Integer age; //Non-primitive to allow @NonNull annotation
	
	@NonNull
	private String email;
	
	private String phoneNumber;
	
	@NonNull
	private Boolean admin = false; //Non-primitive to allow @NonNull annotation
	
	//relationship with orders
	@OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<Orders> orders;
	
	public Accounts(String username, String password, String firstname, String surname, Integer age, String email, String phoneNumber, Boolean admin) {
		this.username = username;
		this.password = this.encrypt(password);
		this.firstname = firstname;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.admin = admin;
	}
	
	public Accounts(Long id, String username, String password, String firstname, String surname, Integer age, String email, String phoneNumber, Boolean admin) {
		this.id = id;
		this.username = username;
		this.password = this.encrypt(password);
		this.firstname = firstname;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.admin = admin;
	}
	
	public void updateFields(Accounts newAccount) {
		this.username = newAccount.getUsername();
		this.password = newAccount.getPassword();;
		this.firstname = newAccount.getFirstname();;
		this.surname = newAccount.getUsername();;
		this.age = newAccount.getAge();
		this.email = newAccount.getEmail();;
		this.phoneNumber = newAccount.getPhoneNumber();
		this.admin = newAccount.isAdmin();;
	}
	
	public String encrypt(String password) {
		String hash = (hashCode + password);
		//TO-DO complete hashing with salt
		return hash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname + ", surname=" + surname + ", age=" + age + ", email=" + email + ", phoneNumber=" + phoneNumber + ", admin=" + admin + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(admin, age, email, firstname, password, phoneNumber, surname, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		return Objects.equals(admin, other.admin)
				&& Objects.equals(age, other.age)
				&& Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname)
				&& Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(surname, other.surname)
				&& Objects.equals(username, other.username);
	}



	
}
