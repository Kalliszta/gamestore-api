package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlatformsTest {
	private Platforms existingPlatform;
	
	@BeforeEach
	void setUpForEach() {
		existingPlatform = new Platforms(1L, "PS4", "PlayStation");
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingPlatform.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetName() {
		//given
		String expected = "PS4";
		//when
		String actual = existingPlatform.getName();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetName() {
		//given
		String expected = "PS3";
		//when
		existingPlatform.setName("PS3");
		String actual = existingPlatform.getName();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetCompany() {
		//given
		String expected = "PlayStation";
		//when
		String actual = existingPlatform.getCompany();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetDescription() {
		//given
		String expected = "Sony";
		//when
		existingPlatform.setCompany("Sony");
		String actual = existingPlatform.getCompany();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "Platforms [id=1, name=PS4, company=PlayStation]";
		//when
		String actual = existingPlatform.toString();
		//then
		assertEquals(expected,actual);
	}

}
