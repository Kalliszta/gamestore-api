package com.qa.gamestore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.gamestore.domain.Games;
import com.qa.gamestore.service.GamesService;


@RestController
@RequestMapping("/gamestore/games")
public class GamesController implements ControllerInterface<Games> {
	
	private GamesService service;
	
	@Autowired
	public GamesController(GamesService service) {
		this.service = service;
	}
	
	//create - POST request
	@PostMapping("/create")
	@Override
	public ResponseEntity<Games> create(@RequestBody Games info) {
		return new ResponseEntity<Games>(this.service.create(info), HttpStatus.CREATED);
		
	}
	
	//readAll - GET request
	@GetMapping("/read/all")
	@Override
	public ResponseEntity<List<Games>> get() {
		return new ResponseEntity<List<Games>>(this.service.readAll(), HttpStatus.OK);
		
	}
	
	//readById - GET request
	@GetMapping("/read/{id}")
	@Override
	public ResponseEntity<Games> get(@PathVariable Long id) {
		return new ResponseEntity<Games>(this.service.readById(id), HttpStatus.OK);
	}
	
	//update - PUT request
	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<Games> update(@PathVariable Long id, @RequestBody Games newInfo) {
		return new ResponseEntity<Games>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	//delete - DELETE request
	@DeleteMapping("/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id),HttpStatus.OK);
	}
	
	// ### custom query mappings go below ###
	
	//Returns all games with the specified name
	@GetMapping("/read/name/{name}")
	public ResponseEntity<List<Games>> getByName(@PathVariable String name) {
		return new ResponseEntity<List<Games>>(this.service.readByName(name), HttpStatus.OK);
	}
	
	//Returns all games equal to or less than the specified age
	@GetMapping("/read/age/{age}")
	public ResponseEntity<List<Games>> getByAge(@PathVariable int age) {
		return new ResponseEntity<List<Games>>(this.service.readByAge(age), HttpStatus.OK);
	}
	
	//Returns all games that match the given input for whether the game is online or not
	@GetMapping("/read/online/{isOnline}")
	public ResponseEntity<List<Games>> getByOnlineGame(@PathVariable boolean isOnline) {
		return new ResponseEntity<List<Games>>(this.service.readByOrderGame(isOnline), HttpStatus.OK);
	}
	
	//Returns all games that are available on the platform with the inputed id
	@GetMapping("/read/platform/{id}")
	public ResponseEntity<List<Games>> getPlatforms(@PathVariable Long id) {
		return new ResponseEntity<List<Games>>(this.service.platformById(id), HttpStatus.OK);
	}
	
	//Returns all games have the genre of the inputed id
	@GetMapping("/read/genre/{id}")
	public ResponseEntity<List<Games>> getGenres(@PathVariable Long id) {
		return new ResponseEntity<List<Games>>(this.service.genreById(id), HttpStatus.OK);
	}
	
}
