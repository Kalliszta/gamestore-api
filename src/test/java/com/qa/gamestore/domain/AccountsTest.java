package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsTest {
	private Accounts existingAccount;
	
	@BeforeEach
	void setUpForEach() {
		existingAccount = new Accounts(1L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingAccount.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetUsername() {
		//given
		String expected = "TestUser";
		//when
		String actual = existingAccount.getUsername();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetUsername() {
		//given
		String expected = "SallySmith";
		//when
		existingAccount.setUsername("SallySmith");
		String actual = existingAccount.getUsername();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetPassword() {
		//given
		String expected = ("pass123");
		//when
		String actual = existingAccount.getPassword();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetPassword() {
		//given
		String expected = ("pass123");
		//when
		existingAccount.setPassword("pass123");
		String actual = existingAccount.getPassword();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetFirstname() {
		//given
		String expected = "Sally";
		//when
		String actual = existingAccount.getFirstname();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetFirstname() {
		//given
		String expected = "Salli";
		//when
		existingAccount.setFirstname("Salli");
		String actual = existingAccount.getFirstname();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetSurname() {
		//given
		String expected = "Smith";
		//when
		String actual = existingAccount.getSurname();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetSurname() {
		//given
		String expected = "Adamson";
		//when
		existingAccount.setSurname("Adamson");
		String actual = existingAccount.getSurname();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetAge() {
		//given
		Integer expected = 23;
		//when
		Integer actual = existingAccount.getAge();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetAge() {
		//given
		Integer expected = 24;
		//when
		existingAccount.setAge(24);
		Integer actual = existingAccount.getAge();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetEmail() {
		//given
		String expected = "sallys@email.com";
		//when
		String actual = existingAccount.getEmail();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetEmail() {
		//given
		String expected = "sallysmith@email.com";
		//when
		existingAccount.setEmail("sallysmith@email.com");
		String actual = existingAccount.getEmail();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetPhoneNumber() {
		//given
		String expected = "07444271155";
		//when
		String actual = existingAccount.getPhoneNumber();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetPhoneNumber() {
		//given
		String expected = "07345271155";
		//when
		existingAccount.setPhoneNumber("07345271155");
		String actual = existingAccount.getPhoneNumber();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testIsAdmin() {
		//given
		Boolean expected = false;
		//when
		Boolean actual = existingAccount.isAdmin();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetAdmin() {
		//given
		Boolean expected = true;
		//when
		existingAccount.setAdmin(true);
		Boolean actual = existingAccount.isAdmin();
		//then
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void testToString() {
		//given
		String expected = "Accounts [id=1, username=TestUser, password=pass123, firstname=Sally, surname=Smith, age=23, email=sallys@email.com, phoneNumber=07444271155, admin=false]";
		//when
		String actual = existingAccount.toString();
		//then
		assertEquals(expected,actual);
	}
}
