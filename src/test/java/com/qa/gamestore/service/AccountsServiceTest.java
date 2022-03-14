package com.qa.gamestore.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.repo.AccountsRepo;

@SpringBootTest
@ActiveProfiles("test")
public class AccountsServiceTest {
	private Long id;
	private Accounts newAccount;
	private Accounts savedAccount;
	
	@Autowired
	private AccountsService service;
	
	@MockBean
	private AccountsRepo repo;
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used
		newAccount = new Accounts("TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
		savedAccount = new Accounts(1L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.repo.save(newAccount)).thenReturn(savedAccount);
		
		//then
		assertThat(this.service.create(newAccount)).isEqualTo(savedAccount);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newAccount);
	}
	
	@Test
	void testReadAll() {
		//given
		//some things set up using setUpForEach
	
		List<Accounts> expectedAccounts = Arrays.asList(
				savedAccount,
				new Accounts(2L, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true),
				new Accounts(3L, "LilyHere", "pass1", "Lily", "Smith", 25, "lily@email.com", "05354664637", false),
				new Accounts(4L, "User3", "pAsSwOrD", "Bob", "Roberts", 12, "roberts@email.com", "07853364637", false),
				new Accounts(5L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false)
				);
		
		//when
		Mockito.when(this.repo.findAll()).thenReturn(expectedAccounts);
		
		//then
		assertThat(this.service.readAll()).isEqualTo(expectedAccounts);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testReadById() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Accounts> optAccount = Optional.of(new Accounts(id, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedAccount);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Accounts> optAccount = Optional.of(new Accounts(id, null, null, null, null, 0, null, null, null)); //optional is all null then same values as originally are used to overwrite the optional values to test if each update
		Accounts updatedAccount = new Accounts(id, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		Mockito.when(this.repo.save(updatedAccount)).thenReturn(updatedAccount);
		
		//then
		assertThat(this.service.update(id, newAccount)).isEqualTo(updatedAccount);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedAccount);
	}
	
	@Test
	void testDelete() {
		//given
		//some things set up using setUpForEach
		id = 1L;

		//when
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//then
		assertThat(this.service.delete(id)).isTrue();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	void testDeleteFail() {
		//given
		//some things set up using setUpForEach
		id = 100L;

		//when
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		
		//then
		assertThat(this.service.delete(id)).isFalse();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
}
