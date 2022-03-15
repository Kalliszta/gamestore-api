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

import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.service.PlatformsService;

@SpringBootTest
public class PlatformsControllerTest {
	private Long id;
	private Platforms newPlatform;
	private Platforms savedPlatform;
	
	@Autowired
	private PlatformsController controller;
	
	@MockBean
	private PlatformsService service;
	
	@BeforeEach
	void setUpForEach() {
		newPlatform = new Platforms("PS4", "PlayStation");
		savedPlatform = new Platforms(1L, "PS4", "PlayStation");
	}
	
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.service.create(newPlatform)).thenReturn(savedPlatform);
		ResponseEntity<Platforms> response = new ResponseEntity<Platforms>(savedPlatform, HttpStatus.CREATED);
		//then
		assertThat(response).isEqualTo(this.controller.create(newPlatform));
		
		//verify
		Mockito.verify(this.service, Mockito.times(1)).create(newPlatform);
	}
	
	@Test
	void testReadAll() {
		//given
		//set up using setUpForEach
		List<Platforms> expectedPlatforms = Arrays.asList(
				savedPlatform,
				new Platforms(2L, "PS3", "PlayStation"),
				new Platforms(3L, "Wii", "Nintendo")
				);
		//when
		Mockito.when(this.service.readAll()).thenReturn(expectedPlatforms);
		ResponseEntity<List<Platforms>> response = new ResponseEntity<List<Platforms>>(expectedPlatforms, HttpStatus.OK);
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
		Mockito.when(this.service.readById(id)).thenReturn(savedPlatform);
		ResponseEntity<Platforms> response = new ResponseEntity<Platforms>(savedPlatform, HttpStatus.OK);
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
		Platforms updateTo = new Platforms("PS4", "SONY");  //each information is changed to see if all are changed

		//when
		
		Mockito.when(this.service.update(id, updateTo)).thenReturn(updateTo);
		ResponseEntity<Platforms> response = new ResponseEntity<Platforms>(updateTo, HttpStatus.ACCEPTED);
		
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