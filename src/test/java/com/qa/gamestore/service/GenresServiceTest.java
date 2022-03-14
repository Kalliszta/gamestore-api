package com.qa.gamestore.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamestore.domain.Genres;
import com.qa.gamestore.repo.GenresRepo;

@SpringBootTest
@ActiveProfiles("test")
public class GenresServiceTest {
	private Long id;
	Long accountId;
	private Genres newGenre;
	private Genres savedGenre;
	
	@Autowired
	private GenresService service;
	
	@MockBean
	private GenresRepo repo;
	
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used		
		newGenre = new Genres("Platformer");
		savedGenre = new Genres(1L, "Platformer");
	}

	//TO-DO fix
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.repo.save(newGenre)).thenReturn(savedGenre);
		
		//then
		assertThat(this.service.create(newGenre)).isEqualTo(savedGenre);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newGenre);
	}
	
	@Test
	void testReadAll() {
		//given
		//some things set up using setUpForEach
		List<Genres> expectedGenres = Arrays.asList(
				savedGenre,
				new Genres(2L, "RPG"),
				new Genres(3L, "Sandbox")
				);
		
		//when
		Mockito.when(this.repo.findAll()).thenReturn(expectedGenres);
		
		//then
		assertThat(this.service.readAll()).isEqualTo(expectedGenres);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testReadById() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Genres> optGenre = Optional.of(new Genres(id, "Platformer"));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGenre);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedGenre);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	//TO-DO fix
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		id = 1L;

		Optional<Genres> optGenre = Optional.of(new Genres(id, null)); //optional is all null then same values as originally are used to overwrite the optional values to test if each update
		Genres updatedGenre = new Genres(id, "Platformer");
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGenre);
		Mockito.when(this.repo.save(updatedGenre)).thenReturn(updatedGenre);
		
		//then
		assertThat(this.service.update(id, newGenre)).isEqualTo(updatedGenre);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedGenre);
	}
	
	@Test
	void testDelete() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Genres> optGenre = Optional.of(new Genres(id, "Platformer"));
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optGenre);
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//then
		assertThat(this.service.delete(id)).isTrue();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
}
