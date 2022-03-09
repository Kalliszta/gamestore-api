package com.qa.gamestore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.service.AccountsService;


@RestController
@RequestMapping("/gamestore/accounts")
public class AccountsController implements ControllerInterface<Accounts> {
	
	
	private AccountsService service;
	@Autowired
	public AccountsController(AccountsService service) {
		this.service = service;
	}
	
	//create - POST request
	@PostMapping("/create")
	@Override
	public ResponseEntity<Accounts> create(@RequestBody Accounts info) {
		return new ResponseEntity<Accounts>(this.service.create(info), HttpStatus.CREATED);
		
	}
	
	//readAll - GET request
	@GetMapping("/read/all")
	@Override
	public ResponseEntity<List<Accounts>> get() {
		return new ResponseEntity<List<Accounts>>(this.service.readAll(), HttpStatus.OK);
		
	}
	
	//readById - GET request
	@GetMapping("/read/{id}")
	@Override
	public ResponseEntity<Accounts> get(@PathVariable Long id) {
		return new ResponseEntity<Accounts>(this.service.readById(id), HttpStatus.OK);
	}
	
	//update - PUT request
	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<Accounts> update(@PathVariable Long id, @RequestBody Accounts newInfo) {
		return new ResponseEntity<Accounts>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	//delete - DELETE request
	@DeleteMapping("/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id),HttpStatus.OK);
	}
	
	//custom query mappings go here
	

	
}
