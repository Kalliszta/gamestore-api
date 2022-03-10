package com.qa.gamestore.service;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.gamestore.domain.Games;

//@Disabled //used to ignore/disable class (use when testing coverage)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql","classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
	public class GamesControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper jsonifier; //maps java object to json
	
	private final String URL = "http://localhost:8080/gamestore/games";
	private Long id = 1L;
	
	@Test
	void testCreate() throws Exception {
		Games testGame = new Games(0L, "TestGame", "A game that doesn't exist", 7, 5.87, false);
		Games expectedGame = new Games(6L, "TestGame", "A game that doesn't exist", 7, 5.87, false);
			
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, URL + "/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(testGame))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = MockMvcResultMatchers.status().isCreated();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedGame));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	
	}
	
	@Test
	void testReadAll() throws Exception {
		//creation of objects in Java as although they exist in test database in Java they aren't existing objects
		List<Games> expectedGames = Arrays.asList(
				new Games(1L, "Elder Scrolls", "An RPG", 18, 15.99, true),
				new Games(2L, "Horizon Zero Dawn", "An RPG that takes place in the future", 16, 29.99, false),
				new Games(3L, "Horizon Forbidden West", "An RPG that takes place in the future", 16, 79.99, false),
				new Games(4L, "Minecraft", "A fun game to play with friends", 7, 19.99, true),
				new Games(5L, "Animal Crossing New Horizons", "The most relaxing game ever", 3, 45.25, true)
				);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, URL + "/read/all");
		
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedGames));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	}
	
	
}
