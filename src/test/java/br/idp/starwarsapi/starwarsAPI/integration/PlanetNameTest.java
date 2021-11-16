package br.idp.starwarsapi.starwarsAPI.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integration.properties")
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlanetNameTest {


	private PlanetRepository planetRepository;

	@Test
	public void shouldFindPlanetName() {
		Planet response = planetRepository.findByName("Stewjon");
		assertEquals("Stewjon", response.getName());
	}

	@Test
	public void shouldNotFindPlanetName() {
		Planet response = planetRepository.findByName("Stewjon");
		assertNull("Terra", response.getName());
	}

}
