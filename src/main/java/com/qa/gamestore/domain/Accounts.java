package com.qa.gamestore.domain;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "accounts")
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
	private int age;
	
	@NonNull
	private String email;
	
	private String phoneNumber;
	
	@NonNull
	private boolean admin = false;
	
	
	//TO-DO add validation
	public Accounts(Long id, String username, String password, String firstname, String surname, int age, String email,
			String phoneNumber, boolean admin) {
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
	
	public Accounts(String username, String password, String firstname, String surname, int age, String email, String phoneNumber, boolean admin) {
		//TO-DO add validation
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



	
}
