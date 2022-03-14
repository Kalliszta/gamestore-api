package com.qa.gamestore.rest;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.qa.gamestore.domain.Genres;
import com.qa.gamestore.service.GenresService;

@SpringBootTest
public class GenresControllerTest {
	private Long id;
	private Genres newGenre;
	private Genres savedGenre;
	
	@Autowired
	private GenresController controller;
	
	@MockBean
	private GenresService service;
	
	@BeforeEach
	void setUpForEach() {
		newGenre = new Genres("Platformer");
		savedGenre = new Genres(1L, "Platformer");
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.service.create(newGenre)).thenReturn(savedGenre);
		ResponseEntity<Genres> response = new ResponseEntity<Genres>(savedGenre, HttpStatus.CREATED);
		//then
		assertThat(response).isEqualTo(this.controller.create(newGenre));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).create(newGenre);
	}
	
	@Test
	void testReadAll() {
		//given
		//set up using setUpForEach
		List<Genres> expectedGenres = Arrays.asList(
				savedGenre,
				new Genres(2L, "RPG"),
				new Genres(3L, "Sandbox")
				);
		//when
		Mockito.when(this.service.readAll()).thenReturn(expectedGenres);
		ResponseEntity<List<Genres>> response = new ResponseEntity<List<Genres>>(expectedGenres, HttpStatus.OK);
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
		Mockito.when(this.service.readById(id)).thenReturn(savedGenre);
		ResponseEntity<Genres> response = new ResponseEntity<Genres>(savedGenre, HttpStatus.OK);
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
		Genres updateTo = new Genres("2D Platformer");  //each information is changed to see if all are changed

		//when
		
		Mockito.when(this.service.update(id, updateTo)).thenReturn(updateTo);
		ResponseEntity<Genres> response = new ResponseEntity<Genres>(updateTo, HttpStatus.ACCEPTED);
		
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
}