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

import com.qa.gamestore.domain.Platforms;
import com.qa.gamestore.repo.PlatformsRepo;


@SpringBootTest
@ActiveProfiles("test")
public class PlatformsServiceTest {
	private Long id;
	Long accountId;
	private Platforms newPlatform;
	private Platforms savedPlatform;
	
	@Autowired
	private PlatformsService service;
	
	@MockBean
	private PlatformsRepo repo;
	
	
	@BeforeEach
	void setUpForEach() { //used for values that are re-used		
		newPlatform = new Platforms("PS4", "PlayStation");
		savedPlatform = new Platforms(1L, "PS4", "PlayStation");
	}

	//TO-DO fix
	@Test
	void testCreate() {
		//given
		//set up using setUpForEach
		
		//when
		Mockito.when(this.repo.save(newPlatform)).thenReturn(savedPlatform);
		
		//then
		assertThat(this.service.create(newPlatform)).isEqualTo(savedPlatform);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newPlatform);
	}
	
	@Test
	void testReadAll() {
		//given
		//some things set up using setUpForEach
		List<Platforms> expectedPlatforms = Arrays.asList(
				savedPlatform,
				new Platforms(2L, "PS5", "PlayStation"),
				new Platforms(3L, "PS3", "PlayStation")
				);
		
		//when
		Mockito.when(this.repo.findAll()).thenReturn(expectedPlatforms);
		
		//then
		assertThat(this.service.readAll()).isEqualTo(expectedPlatforms);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testReadById() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Platforms> optPlatform = Optional.of(new Platforms(id, "PS4", "PlayStation"));
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optPlatform);
		
		//then
		assertThat(this.service.readById(id)).isEqualTo(savedPlatform);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	//TO-DO fix
	@Test
	void testUpdate() {
		//given
		//some things set up using setUpForEach
		id = 1L;

		Optional<Platforms> optPlatform = Optional.of(new Platforms(id, null, null)); //optional is all null then same values as originally are used to overwrite the optional values to test if each update
		Platforms updatedPlatform = new Platforms(id, "PS4", "PlayStation");
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optPlatform);
		Mockito.when(this.repo.save(updatedPlatform)).thenReturn(updatedPlatform);
		
		//then
		assertThat(this.service.update(id, newPlatform)).isEqualTo(updatedPlatform);
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPlatform);
	}
	
	@Test
	void testDelete() {
		//given
		//some things set up using setUpForEach
		id = 1L;
		Optional<Platforms> optPlatform = Optional.of(new Platforms(id, "PS4", "PlayStation"));
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optPlatform);
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//then
		assertThat(this.service.delete(id)).isTrue();
		
		//verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
}
