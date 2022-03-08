package com.qa.gamestore.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	private String firstname;
	private String surname;
	private int age;
	private String email;
	private String phoneNumber;
	private boolean admin = false;
	
	public Accounts(String username, String password, String firstname, String surname, int age, String email, String phoneNumber, boolean admin) {
		this.username = username;
		this.password = password;
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
	
	
}
