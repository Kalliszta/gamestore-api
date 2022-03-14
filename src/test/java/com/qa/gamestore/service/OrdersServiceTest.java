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
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.domain.OrderGames;
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.repo.AccountsRepo;
import com.qa.gamestore.repo.GamesRepo;
import com.qa.gamestore.repo.OrderGamesRepo;
import com.qa.gamestore.repo.OrdersRepo;

@SpringBootTest
@ActiveProfiles("test")
public class OrdersServiceTest {
	private Long id;
	Long accountId;
	private Orders newOrder;
	private Orders savedOrder;
	
	@Autowired
	private OrdersService service;
	
	@MockBean
	private OrdersRepo repo;
	@MockBean
	private OrderGamesRepo ordGRepo;
	@MockBean
	private AccountsRepo aRepo;
	@MockBean
	private GamesRepo gRepo;
	
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used		
		newOrder = new Orders(1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		savedOrder = new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
	}

	//TO-DO fix
	@Test
	void testCreate() {
		//given
		//some things set up using setUpForEach
		accountId = 1L;
		Optional<Accounts> optAccount = Optional.of(new Accounts (accountId, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true));
		
		//when
		Mockito.when(this.aRepo.findById(accountId)).thenReturn(optAccount);
		Mockito.when(this.repo.save(newOrder)).thenReturn(savedOrder);
		
		//then
		assertThat(this.service.create(newOrder)).isEqualTo(savedOrder);
		
		//verify
		Mockito.verify(this.aRepo, Mockito.times(1)).findById(accountId);
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
				new Orders(3L, 1L, Timestamp.valueOf("2022-03-12 07:00:12.000")),
				new Orders(4L, 1L, Timestamp.valueOf("2022-03-11 20:09:58.000")),
				new Orders(5L, 1L, Timestamp.valueOf("2022-03-12 07:00:12.000"))
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
		Optional<Orders> optOrder = Optional.of(new Orders(1L, Timestamp.valueOf("2022-03-12 13:12:18.000")));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optOrder);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedOrder);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	//TO-DO fix
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		accountId = 1L;
		Optional<Accounts> optAccount = Optional.of(new Accounts (accountId, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true));
		Optional<Orders> optOrder = Optional.of(new Orders(id, null, null)); //optional is all null then same values as originally are used to overwrite the optional values to test if each update
		Orders updatedOrder = new Orders(1L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		
		//when
		Mockito.when(this.aRepo.findById(accountId)).thenReturn(optAccount);
		Mockito.when(this.repo.findById(id)).thenReturn(optOrder);
		Mockito.when(this.repo.save(updatedOrder)).thenReturn(updatedOrder);
		
		//then
		assertThat(this.service.update(id, newOrder)).isEqualTo(updatedOrder);
		
		//verify
		Mockito.verify(this.aRepo, Mockito.times(1)).findById(id);
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
	
	@Test
	void testAdd() {
		//given
		Long orderId = 1L;
		Long gameId = 1L;

		Optional<Orders> optOrder = Optional.of(new Orders(orderId, 1L, Timestamp.valueOf("2022-03-12 13:12:18.000")));
		Optional<Games> optGame = Optional.of(new Games(gameId, "LittleBigPlanet", "Best platformer ever", 7, 29.99, true));
		OrderGames newOrderGame = new OrderGames(1L, 1L);
		OrderGames savedOrderGame = new OrderGames(1L, 1L, 1L);
		
		//when
		Mockito.when(this.repo.findById(orderId)).thenReturn(optOrder);
		Mockito.when(this.gRepo.findById(gameId)).thenReturn(optGame);
		Mockito.when(this.ordGRepo.save(newOrderGame)).thenReturn(savedOrderGame);	
		
		//then
		assertThat(this.service.add(newOrderGame)).isEqualTo(savedOrderGame);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(orderId);
		Mockito.verify(this.gRepo, Mockito.times(1)).findById(gameId);
		Mockito.verify(this.ordGRepo, Mockito.times(1)).save(newOrderGame);
	}
	
}
