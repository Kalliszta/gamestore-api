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
	import com.qa.gamestore.domain.Accounts;

	@Disabled //used to ignore/disable class (use when testing coverage)
	@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
	@AutoConfigureMockMvc
	@Sql(scripts = {"classpath:schema-test.sql","classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@ActiveProfiles(profiles = "test")
	public class AccountsControllerIntegrationTest {

		@Autowired
		private MockMvc mock;
		
		@Autowired
		private ObjectMapper jsonifier; //maps java object to json
		
		private final String URL = "http://localhost:8080/gamestore/accounts";
		private Long id;
		
		// ### Tests for basic CRUD endpoints ###
		@Test
		void testCreate() throws Exception {
			Accounts testAccount = new Accounts(0L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
			Accounts expectedAccount = new Accounts(5L, "TestUser", "pass123", "Sally", "Smith", 23, "sallys@email.com", "07444271155", false);
				
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.POST, URL + "/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonifier.writeValueAsString(testAccount))
					.accept(MediaType.APPLICATION_JSON);
			
			ResultMatcher status = MockMvcResultMatchers.status().isCreated();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedAccount));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		
		}
		
		@Test
		void testReadAll() throws Exception {
			//creation of objects in Java as although they exist in test database in Java they aren't existing objects
			List<Accounts> expectedAccounts = Arrays.asList(
					new Accounts(1L, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true),
					new Accounts(2L, "LilyHere", "pass1", "Lily", "Smith", 25, "lily@email.com", "05354664637", false),
					new Accounts(3L, "User3", "pAsSwOrD", "Bob", "Roberts", 12, "roberts@email.com", "07853364637", false),
					new Accounts(4L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false)
					);
			
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.GET, URL + "/read/all");
			
			ResultMatcher status = MockMvcResultMatchers.status().isOk();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedAccounts));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		}
		
		@Test
		void testReadById() throws Exception {
			id = 1L;
			//creation of object in Java as although it exists in test database in Java it doesn't exist as an object
			Accounts expectedAccount = new Accounts(id, "KallisztaG", "password123", "Kalliszta", "Grof", 19, "kalg@email.com", "07474354663", true);
			
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.GET, URL + "/read/" + id);
			
			ResultMatcher status = MockMvcResultMatchers.status().isOk();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedAccount));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		}
		
		@Test
		void testUpdate() throws Exception {
			id = 2L;
			Accounts expectedAccount = new Accounts(id, "LRose", "newPassword", "Lilian", "Rose", 25, "rose@email.com", "07111311317", true); //values are each changed to see if all change
				
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.PUT, URL + "/update/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonifier.writeValueAsString(expectedAccount))
					.accept(MediaType.APPLICATION_JSON);
			
			ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedAccount));
		
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
