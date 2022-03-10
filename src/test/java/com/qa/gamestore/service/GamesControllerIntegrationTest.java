package com.qa.gamestore.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

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
		Games testGame = new Games(0L, "TestGame", "A game that doesn't exist", 7, 5.87D, 0);
		
	}
	
}
