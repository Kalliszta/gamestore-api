package com.qa.gamestore.rest;

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
import com.qa.gamestore.domain.Platforms;

//@Disabled //used to ignore/disable class (use when testing coverage)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql","classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class PlatformsControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper jsonifier; //maps java object to json
	
	private final String URL = "http://localhost:8080/gamestore/platforms";
	private Long id;
	
	// ### Tests for basic CRUD endpoints ###
	@Test
	void testCreate() throws Exception {
		Platforms testPlatform = new Platforms(0L, "Wii", "Nintendo");
		Platforms expectedPlatform = new Platforms(8L, "Wii", "Nintendo");
			
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, URL + "/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(testPlatform))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = MockMvcResultMatchers.status().isCreated();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedPlatform));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	
	}
	
	@Test
	void testReadAll() throws Exception {
		//creation of objects in Java as although they exist in test database in Java they aren't existing objects
		List<Platforms> expectedPlatforms = Arrays.asList(
				new Platforms(1L, "PC", "Microsoft"),
				new Platforms(2L, "PC", "Steam"),
				new Platforms(3L, "PC", "Epic Games"),
				new Platforms(4L, "PS3", "PlayStation"),
				new Platforms(5L, "PS4", "PlayStation"),
				new Platforms(6L, "PS5", "PlayStation"),
				new Platforms(7L, "Nintendo Switch", "Nintendo")
				);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, URL + "/read/all");
		
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedPlatforms));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	}
	
	@Test
	void testReadById() throws Exception {
		id = 1L;
		//creation of object in Java as although it exists in test database in Java it doesn't exist as an object
		Platforms expectedPlatform = new Platforms(1L, "PC", "Microsoft");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, URL + "/read/" + id);
		
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedPlatform));
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	}
	
	@Test
	void testUpdate() throws Exception {
		id = 1L;
		Platforms expectedPlatform = new Platforms(id, "Pc", "MICROSOFT"); //values are each changed to see if all change (excluding the id)
			
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, URL + "/update/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(expectedPlatform))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedPlatform));
	
		this.mock.perform(mockRequest).andExpect(status).andExpect(content);
	
	}
	
	
	@Test
	void testDelete() throws Exception {
		id = 1L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, URL + "/remove/" + id);
		
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		ResultMatcher content = MockMvcResultMatchers.content().string("true"); //expect boolean true but can check using "true" as there is no ResultMatchers for boolean
		
		this.mock.perform(mockRequest).andExpect(status).andExpect(content); //TO-DO joined tables isn't letting it be deleted FIX
	}
}
