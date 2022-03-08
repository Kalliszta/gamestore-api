package com.qa.gamestore.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.service.AccountsService;

@RestController
@RequestMapping("/gamestore/accounts")
public class AccountsController implements ControllerInterface<Accounts> {

	private AccountsService service;
	Authentication auth;

	public AccountsController(AccountsService service) {
		this.service = service;
		this.auth = SecurityContextHolder.getContext().getAuthentication();
	}

	// create - POST request
	@PostMapping("/create")
	@Override
	public ResponseEntity<Accounts> create(@RequestBody Accounts info) {
		return new ResponseEntity<Accounts>(this.service.create(info), HttpStatus.CREATED);

	}

	// readAll - GET request
	@GetMapping("/read/all")
	@Override
	public ResponseEntity<List<Accounts>> get() {
		return new ResponseEntity<List<Accounts>>(this.service.readAll(), HttpStatus.OK);

	}

	// readById - GET request
	@GetMapping("/read/{id}")
	@Override
	public ResponseEntity<Accounts> get(@PathVariable Long id) {
		return new ResponseEntity<Accounts>(this.service.readById(id), HttpStatus.OK);
	}

	// update - PUT request
	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<Accounts> update(@PathVariable Long id, @RequestBody Accounts newInfo) {
		return new ResponseEntity<Accounts>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	// delete - DELETE request
	@DeleteMapping("/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id), HttpStatus.OK);
	}

	// custom query mappings go here

//	@GetMapping("/login")
//	public ResponseEntity<Boolean> login() {
//		return new ResponseEntity<Boolean>(this.service.validateInput(), HttpStatus.ACCEPTED);
//
//	}

//	@RequestMapping(value = "/username", method = RequestMethod.GET)
//	@ResponseBody
//	public String currentUserName() {
//		return auth.getName();
//	}

}
