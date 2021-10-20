package br.idp.starwarsapi.starwarsAPI.service;

import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.idp.starwarsapi.starwarsAPI.controller.PlanetController;
import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;

import br.idp.starwarsapi.starwarsAPI.model.PlanetSearch;
import br.idp.starwarsapi.starwarsAPI.model.SwApiPlanet;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SwApiService {

	private RestTemplate restTemplate = new RestTemplate();

	Logger log = LoggerFactory.getLogger(PlanetController.class);

	private String swap_url = "https://swapi.co/api";

	@Cacheable(value = "getSwapiPlanetsName")
	public SwApiPlanet getSwapiPlanetsName(String name) throws ConnectionException, PlanetNotFoundException {
		log.info("Acessando api para buscar planetas pelo nome...");

		if (!checkConnection()) {
			throw new ConnectionException("Not connection detection");
		}

		String SW_URL = "https://swapi.dev/api/planets?search=" + name;

		PlanetSearch planetSearch = restTemplate.getForObject(SW_URL, PlanetSearch.class);

		for (SwApiPlanet swApiPlanet : planetSearch.getResults()) {
			if (swApiPlanet.getName().equals(name)) {
				return swApiPlanet;
			}
		}
		throw new PlanetNotFoundException("No such planet on the star wars api with this name");
	}

	@Cacheable(value = "getSwapiPlanetsId")
	public SwApiPlanet getSwapiPlanetsId(Long id) throws ConnectionException, PlanetNotFoundException {
		log.info("Acessando api para buscar planetas pelo id...");

		if (!checkConnection()) {
			throw new ConnectionException("Not connection detection");
		}

		if (id <= 60) {
			String SW_URL = "https://swapi.dev/api/planets/" + id;

			return restTemplate.getForObject(SW_URL, SwApiPlanet.class);

		}
		throw new PlanetNotFoundException("Planet not exist in the API");

	}

	private boolean checkConnection() {
		try {
			URL url = new URL(swap_url);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
