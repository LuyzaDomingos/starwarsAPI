package br.idp.starwarsapi.starwarsAPI.unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PlanetRepositoryTest {

	@Autowired
	private PlanetRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void shoulLoadPlanetName() {
		String namePlanet = "Dantooine";
		
		Planet dantoonie  = new Planet();
		
		dantoonie.setName(namePlanet);
		dantoonie.setClimate("temperate");
		em.persist(dantoonie);
		
		
		Planet planet  = repository.findByName(namePlanet);
		Assert.assertNotNull(planet);
		Assert.assertEquals(namePlanet, planet.getName());
			
	}
	
	@Test
	public void shoulNotLoadPlanetNameNotRegister() {
		String namePlanet = "Terra";
		Planet planet  = repository.findByName(namePlanet);
		Assert.assertNull(planet);	
	}


}
