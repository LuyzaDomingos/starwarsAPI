package br.idp.starwarsapi.starwarsAPI.service;

import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.SwApiPlanet;

class SwApiServiceTest {
	
	@Autowired
	private SwApiService swApiService;


//	@Test
//	public void shouldFindPlanetsId(Long id) throws ConnectionException, PlanetNotFoundException {
//		SwApiPlanet responseApiPlanet  = swApiService.getSwapiPlanetsId(id);
//		assertThat(responseApiPlanet).isNotEmpty();
//		
//	}

}
