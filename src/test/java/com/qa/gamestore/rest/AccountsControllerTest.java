package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.service.AccountsService;

@SpringBootTest
public class AccountsControllerTest {
	private Long id;
	private Accounts newAccount;
	private Accounts savedAccount;
	
	@Autowired
	private AccountsController controller;
	
	@MockBean
	private AccountsService service;
	
	
	@BeforeEach
	void setUpForEach() {
		newAccount = new Accounts("TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
		savedAccount = new Accounts(1L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.service.create(newAccount)).thenReturn(savedAccount);
		ResponseEntity<Accounts> response = new ResponseEntity<Accounts>(savedAccount, HttpStatus.CREATED);
		//then
		assertThat(response).isEqualTo(this.controller.create(newAccount));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).create(newAccount);
	}
	
	@Test
	void testReadAll() {
		//given
		//set up using setUpForEach
		List<Accounts> expectedAccounts = Arrays.asList(
				savedAccount,
				new Accounts(2L, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true),
				new Accounts(3L, "LilyHere", "pass1", "Lily", "Smith", 25, "lily@email.com", "05354664637", false),
				new Accounts(4L, "User3", "pAsSwOrD", "Bob", "Roberts", 12, "roberts@email.com", "07853364637", false),
				new Accounts(5L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false)
				);
		//when
		Mockito.when(this.service.readAll()).thenReturn(expectedAccounts);
		ResponseEntity<List<Accounts>> response = new ResponseEntity<List<Accounts>>(expectedAccounts, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.get());
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}
	
	@Test
	void testReadById() {
		//given
		//set up using setUpForEach
		id = 1L;
		
		//when
		Mockito.when(this.service.readById(id)).thenReturn(savedAccount);
		ResponseEntity<Accounts> response = new ResponseEntity<Accounts>(savedAccount, HttpStatus.OK);
		//then
		assertThat(response).isEqualTo(this.controller.get(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).readById(id);
	}
	
	@Test
	void testUpdate() {
		//given
		//set up using setUpForEach
		id = 1L;
		Accounts updateTo = new Accounts(id, "LRose", "newPassword", "Lilian", "Rose", 25, "rose@email.com", "07111311317", true);  //each information is changed to see if all are changed

		//when
		
		Mockito.when(this.service.update(id, updateTo)).thenReturn(updateTo);
		ResponseEntity<Accounts> response = new ResponseEntity<Accounts>(updateTo, HttpStatus.ACCEPTED);
		
		//then
		assertThat(response).isEqualTo(this.controller.update(id, updateTo));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).update(id, updateTo);
	}
	
	@Test
	void testDelete() {
		//given
		id = 1L;
		
		//when
		Mockito.when(this.service.delete(id)).thenReturn(true);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		//then
		assertThat(response).isEqualTo(this.controller.delete(id));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).delete(id);
	}

}
