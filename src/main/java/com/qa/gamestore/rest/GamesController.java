package com.qa.gamestore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.gamestore.domain.GameGenres;
import com.qa.gamestore.domain.GamePlatforms;
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.service.GamesService;


@RestController
@RequestMapping("/gamestore")
public class GamesController implements ControllerInterface<Games> {
	
	private GamesService service;
	
	@Autowired
	public GamesController(GamesService service) {
		this.service = service;
	}
	
	//create - POST request
	@PostMapping("/games/create")
	
	public ResponseEntity<Games> create(@RequestBody Games info) {
		return new ResponseEntity<Games>(this.service.create(info), HttpStatus.CREATED);
		
	}
	
	//readAll - GET request
	@GetMapping("/games/read/all")
	@Override
	public ResponseEntity<List<Games>> get() {
		return new ResponseEntity<List<Games>>(this.service.readAll(), HttpStatus.OK);
		
	}
	
	//readById - GET request
	@GetMapping("/games/read/{id}")
	@Override
	public ResponseEntity<Games> get(@PathVariable Long id) {
		return new ResponseEntity<Games>(this.service.readById(id), HttpStatus.OK);
	}
	
	//update - PUT request
	@PutMapping("/games/update/{id}")
	@Override
	public ResponseEntity<Games> update(@PathVariable Long id, @RequestBody Games newInfo) {
		return new ResponseEntity<Games>(this.service.update(id, newInfo), HttpStatus.ACCEPTED);
	}

	//delete - DELETE request
	@DeleteMapping("/games/remove/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id),HttpStatus.OK);
	}
	
	// ### custom query mappings go below ###
	
	//Returns all games with the specified name
	@GetMapping("/games/read/name/{name}")
	public ResponseEntity<List<Games>> getByName(@PathVariable String name) {
		return new ResponseEntity<List<Games>>(this.service.readByName(name), HttpStatus.OK);
	}
	
	//Returns all games equal to or less than the specified age
	@GetMapping("/games/read/age/{age}")
	public ResponseEntity<List<Games>> getByAge(@PathVariable Integer age) {
		return new ResponseEntity<List<Games>>(this.service.readByAge(age), HttpStatus.OK);
	}
	
	//Returns all games equal to or less than the specified cost
	@GetMapping("/games/read/cost/{cost}")
	public ResponseEntity<List<Games>> getByCost(@PathVariable Double cost) {
		return new ResponseEntity<List<Games>>(this.service.readByCost(cost), HttpStatus.OK);
	}
	
	//Returns all games that match the given input for whether the game is online or not
	@GetMapping("/games/read/online/{isOnline}")
	public ResponseEntity<List<Games>> getByOnlineGame(@PathVariable Boolean isOnline) {
		return new ResponseEntity<List<Games>>(this.service.readByOnlineGame(isOnline), HttpStatus.OK);
	}
	
	//Returns all games that are available on the platform with the inputed id
	@GetMapping("/games/read/platform/{id}")
	public ResponseEntity<List<Games>> getByPlatform(@PathVariable Long id) {
		return new ResponseEntity<List<Games>>(this.service.platformById(id), HttpStatus.OK);
	}
	
	//Returns all games that have the genre of the inputed id
	@GetMapping("/games/read/genre/{id}")
	public ResponseEntity<List<Games>> getByGenre(@PathVariable Long id) {
		return new ResponseEntity<List<Games>>(this.service.genreById(id), HttpStatus.OK);
	}
	
	//Returns all games that have the order id of the inputed id
	@GetMapping("orders/read/{id}/items")
	public ResponseEntity<List<Games>> items(@PathVariable Long id) {
		return new ResponseEntity<List<Games>>(this.service.items(id), HttpStatus.OK);
	}
	
	//Adds a platform to a game
	@PostMapping("/games/add/platform")
	public ResponseEntity<GamePlatforms> add(@RequestBody GamePlatforms info) {
		return new ResponseEntity<GamePlatforms>(this.service.add(info), HttpStatus.ACCEPTED);
	}
	
	//Adds a genre to a game
	@PostMapping("/games/add/genre")
	public ResponseEntity<GameGenres> add(@RequestBody GameGenres info) {
		return new ResponseEntity<GameGenres>(this.service.add(info), HttpStatus.ACCEPTED);
	}

}
