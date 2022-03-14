package com.qa.gamestore.rest;

	import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.qa.gamestore.domain.OrderGames;
import com.qa.gamestore.domain.Orders;

	//@Disabled //used to ignore/disable class (use when testing coverage)
	@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
	@AutoConfigureMockMvc
	@Sql(scripts = {"classpath:schema-test.sql","classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@ActiveProfiles(profiles = "test")
	public class OrdersControllerIntegrationTest {

		@Autowired
		private MockMvc mock;
		
		@Autowired
		private ObjectMapper jsonifier; //maps java object to json
		
		private final String URL = "http://localhost:8080/gamestore/orders";
		private Long id;
		
		// ### Tests for basic CRUD endpoints ###
		@Test
		void testCreate() throws Exception {
			Orders testOrder = new Orders(0L, 4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
			Orders expectedOrder = new Orders(6L, 4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
				
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.POST, URL + "/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonifier.writeValueAsString(testOrder))
					.accept(MediaType.APPLICATION_JSON);
			
			ResultMatcher status = MockMvcResultMatchers.status().isCreated();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedOrder));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		
		}
		
		
		@Test
		void testReadAll() throws Exception {
			//creation of objects in Java as although they exist in test database in Java they aren't existing objects
			List<Orders> expectedOrders = Arrays.asList(
					new Orders(1L, 1L, Timestamp.valueOf("2022-03-12 13:08:45.000")),
					new Orders(2L, 1L, Timestamp.valueOf("2022-03-11 08:56:32.000")),
					new Orders(3L, 2L, Timestamp.valueOf("2022-03-12 07:00:12.000")),
					new Orders(4L, 2L, Timestamp.valueOf("2022-03-11 20:09:58.000")),
					new Orders(5L, 3L, Timestamp.valueOf("2022-03-12 07:00:12.000"))
					);
			
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.GET, URL + "/read/all");
			
			ResultMatcher status = MockMvcResultMatchers.status().isOk();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedOrders));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		}
		
		@Test
		void testReadById() throws Exception {
			id = 1L;
			//creation of object in Java as although it exists in test database in Java it doesn't exist as an object
			Orders expectedOrder = new Orders(id, 1L, Timestamp.valueOf("2022-03-12 13:08:45.000"));
			
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.GET, URL + "/read/" + id);
			
			ResultMatcher status = MockMvcResultMatchers.status().isOk();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedOrder));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		}
		
		@Test
		void testUpdate() throws Exception {
			id = 2L;
			Orders expectedOrder = new Orders(id, 1L, Timestamp.valueOf("2022-03-11 08:56:32.000")); //values are each changed to see if all change (excluding the id)
				
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.PUT, URL + "/update/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonifier.writeValueAsString(expectedOrder))
					.accept(MediaType.APPLICATION_JSON);
			
			ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedOrder));
		
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
		
		@Test
		void testAdd() throws Exception {
			OrderGames testOrderGame = new OrderGames(0L, 1L, 1L);
			OrderGames expectedOrderGame = new OrderGames(6L, 1L, 1L);
				
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
					.request(HttpMethod.POST, URL + "/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonifier.writeValueAsString(testOrderGame))
					.accept(MediaType.APPLICATION_JSON);
			
			ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
			ResultMatcher content = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedOrderGame));
			
			this.mock.perform(mockRequest).andExpect(status).andExpect(content);
		
		}
}
