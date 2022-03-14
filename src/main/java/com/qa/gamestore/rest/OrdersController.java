package com.qa.gamestore.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.domain.OrderGames;
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.service.OrdersService;


@RestController
@RequestMapping("/gamestore/orders")
public class OrdersController implements ControllerInterface<Orders> {
	
	private OrdersService service;
	@Autowired
	public OrdersController(OrdersService service) {
		this.service = service;
	}
	
	//create - POST request
	@PostMapping("/create")
	@Override
	public ResponseEntity<Orders> create(@RequestBody Orders info) {
		return new ResponseEntity<Orders>(this.service.create(info), HttpStatus.CREATED);
	}
	
	//readAll - GET request
	@GetMapping("/read/all")
	@Override
	public ResponseEntity<List<Orders>> get() {
		return new ResponseEntity<List<Orders>>(this.service.readAll(), HttpStatus.OK);
	}
	
	//readById - GET request
	@GetMapping("/read/{id}")
	@Override
	public ResponseEntity<Orders> get(@PathVariable Long id) {
		return new ResponseEntity<Orders>(this.service.readById(id), HttpStatus.OK);
	}
	
	//update - PUT request
	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<Orders> update(@PathVariable Long id, @RequestBody Orders newInfo) {
		return new ResponseEntity<Orders>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	//delete - DELETE request
	@DeleteMapping("/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id),HttpStatus.OK);
	}
	
	// ### custom query mappings go below ###
	
	@PostMapping("/add")
	public ResponseEntity<OrderGames> add(@RequestBody OrderGames info) {
		return new ResponseEntity<OrderGames>(this.service.add(info), HttpStatus.ACCEPTED);
	}

	
}
