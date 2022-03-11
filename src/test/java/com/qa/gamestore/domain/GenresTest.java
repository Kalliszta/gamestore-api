package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenresTest {
	private Genres existingGenre;
	
	@BeforeEach
	void setUpForEach() {
		existingGenre = new Genres(1L, "Platformer");
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingGenre.getId();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetGenre() {
		//given
		String expected = "Platformer";
		//when
		String actual = existingGenre.getGenre();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSetGenre() {
		//given
		String expected = "2D Platformer";
		//when
		existingGenre.setGenre("2D Platformer");
		String actual = existingGenre.getGenre();
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void testToString() {
		//given
		String expected = "Genres [id=1, genre=Platformer]";
		//when
		String actual = existingGenre.toString();
		//then
		assertEquals(expected,actual);
	}

}

