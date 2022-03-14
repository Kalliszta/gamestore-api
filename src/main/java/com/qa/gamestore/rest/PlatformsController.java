package com.qa.gamestore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.service.PlatformsService;

@RestController
@RequestMapping("/gamestore/platforms")
public class PlatformsController implements ControllerInterface<Platforms> {
	
	private PlatformsService service;
	
	@Autowired
	public PlatformsController(PlatformsService service) {
		this.service = service;
	}
	
	//create - POST request
	@PostMapping("/create")
	@Override
	public ResponseEntity<Platforms> create(@RequestBody Platforms info) {
		return new ResponseEntity<Platforms>(this.service.create(info), HttpStatus.CREATED);
	}
	
	//readAll - GET request
	@GetMapping("/read/all")
	@Override
	public ResponseEntity<List<Platforms>> get() {
		return new ResponseEntity<List<Platforms>>(this.service.readAll(), HttpStatus.OK);
		
	}
	
	//readById - GET request
	@GetMapping("/read/{id}")
	@Override
	public ResponseEntity<Platforms> get(@PathVariable Long id) {
		return new ResponseEntity<Platforms>(this.service.readById(id), HttpStatus.OK);
	}
	
	//update - PUT request
	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<Platforms> update(@PathVariable Long id, @RequestBody Platforms newInfo) {
		return new ResponseEntity<Platforms>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	//delete - DELETE request
	@DeleteMapping("/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id),HttpStatus.OK);
	}
}
