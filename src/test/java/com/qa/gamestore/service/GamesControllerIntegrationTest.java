package com.qa.gamestore.service;

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
		Games expectedGame = new Games(5L, "TestGame", "A game that doesn't exist", 7, 5.87, false);
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, URL + "/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(testGame))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = MockMvcResultMatchers.status().isCreated();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedGame));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	
	}
	
}
