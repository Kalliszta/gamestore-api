package com.qa.gamestore.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
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
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.repo.AccountsRepo;
import com.qa.gamestore.repo.OrdersRepo;

@SpringBootTest
@ActiveProfiles("test")
public class OrdersServiceTest {
	private Long id;
	private Orders newOrder;
	private Orders savedOrder;
	
	@Autowired
	private OrdersService service;
	
	@MockBean
	private OrdersRepo repo;
	@MockBean
	private AccountsRepo accRepo;
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used
		List<Accounts> listOfAccounts = Arrays.asList(
				new Accounts(1L, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true),
				new Accounts(2L, "LilyHere", "pass1", "Lily", "Smith", 25, "lily@email.com", "05354664637", false),
				new Accounts(3L, "User3", "pAsSwOrD", "Bob", "Roberts", 12, "roberts@email.com", "07853364637", false),
				new Accounts(4L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false)
				);
		for (Accounts a: listOfAccounts) {
			accRepo.save(a);
		}
		
		newOrder = new Orders(4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		savedOrder = new Orders(1L, 4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.repo.save(newOrder)).thenReturn(savedOrder);
		
		//then
		assertThat(this.service.create(newOrder)).isEqualTo(savedOrder);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newOrder);
	}
	
	@Test
	void testReadAll() {
		//given
		//some things set up using setUpForEach
	
		List<Orders> expectedOrders = Arrays.asList(
				savedOrder,
				new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:08:45.000")),
				new Orders(2L, 1L, Timestamp.valueOf("2022-03-11 08:56:32.000")),
				new Orders(3L, 2L, Timestamp.valueOf("2022-03-12 07:00:12.000")),
				new Orders(4L, 2L, Timestamp.valueOf("2022-03-11 20:09:58.000")),
				new Orders(5L, 3L, Timestamp.valueOf("2022-03-12 07:00:12.000"))
				);
		
		//when
		Mockito.when(this.repo.findAll()).thenReturn(expectedOrders);
		
		//then
		assertThat(this.service.readAll()).isEqualTo(expectedOrders);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testReadById() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Orders> optOrder = Optional.of(new Orders(4L, Timestamp.valueOf("2022-03-12 13:12:18.000")));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optOrder);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedOrder);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Orders> optOrder = Optional.of(new Orders(id, null, null)); //optional is all null then same values as originally are used to overwrite the optional values to test if each update
		Orders updatedOrder = new Orders(4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optOrder);
		Mockito.when(this.repo.save(updatedOrder)).thenReturn(updatedOrder);
		
		//then
		assertThat(this.service.update(id, newOrder)).isEqualTo(updatedOrder);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedOrder);
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
