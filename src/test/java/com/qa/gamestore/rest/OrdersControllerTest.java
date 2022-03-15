package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
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

import com.qa.gamestore.domain.OrderGames;
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.service.OrdersService;

@SpringBootTest
public class OrdersControllerTest {
	private Long id;
	private Orders newOrder;
	private Orders savedOrder;
	
	@Autowired
	private OrdersController controller;
	
	@MockBean
	private OrdersService service;
	
	@BeforeEach
	void setUpForEach() {
		newOrder = new Orders(4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
		savedOrder = new Orders(1L, 4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.service.create(newOrder)).thenReturn(savedOrder);
		ResponseEntity<Orders> response = new ResponseEntity<Orders>(savedOrder, HttpStatus.CREATED);
		//then
		assertThat(response).isEqualTo(this.controller.create(newOrder));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).create(newOrder);
	}
	
	@Test
	void testReadAll() {
		//given
		//set up using setUpForEach
		List<Orders> expectedOrders = Arrays.asList(
				savedOrder,
				new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:08:45.000")),
				new Orders(2L, 1L, Timestamp.valueOf("2022-03-11 08:56:32.000")),
				new Orders(3L, 2L, Timestamp.valueOf("2022-03-12 07:00:12.000")),
				new Orders(4L, 2L, Timestamp.valueOf("2022-03-11 20:09:58.000")),
				new Orders(5L, 3L, Timestamp.valueOf("2022-03-12 07:00:12.000"))
				);
		//when
		Mockito.when(this.service.readAll()).thenReturn(expectedOrders);
		ResponseEntity<List<Orders>> response = new ResponseEntity<List<Orders>>(expectedOrders, HttpStatus.OK);
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
		Mockito.when(this.service.readById(id)).thenReturn(savedOrder);
		ResponseEntity<Orders> response = new ResponseEntity<Orders>(savedOrder, HttpStatus.OK);
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
		Orders updateTo = new Orders(2L, Timestamp.valueOf("2022-03-11 12:12:45.000"));  //each information is changed to see if all are changed

		//when
		
		Mockito.when(this.service.update(id, updateTo)).thenReturn(updateTo);
		ResponseEntity<Orders> response = new ResponseEntity<Orders>(updateTo, HttpStatus.ACCEPTED);
		
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

	@Test
	void testAdd() {
		//given
		OrderGames newOrderGame = new OrderGames(1L, 1L);
		OrderGames savedOrderGame = new OrderGames(1L, 1L, 1L);
		//when
		Mockito.when(this.service.add(newOrderGame)).thenReturn(savedOrderGame);
		ResponseEntity<OrderGames> response = new ResponseEntity<OrderGames>(savedOrderGame, HttpStatus.ACCEPTED);
		//then
		assertThat(response).isEqualTo(this.controller.add(newOrderGame));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).add(newOrderGame);
	}
}
