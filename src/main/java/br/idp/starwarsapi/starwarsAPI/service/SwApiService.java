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

		String SW_URL = "https://swapi.co/api/planets?search=" + name;

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

		String SW_URL = "https://swapi.co/api/planets/" + id;

		return restTemplate.getForObject(SW_URL, SwApiPlanet.class);

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

//	@Autowired
//	private RestTemplate restTemplate;
//

//
//	public List<Planet> getAllPlanets() throws ConnectionException {
//		if (!checkConnection()) {
//			throw new ConnectionException("Conection not detected");
//		}
//
//		String URL = SW_URL + "/planets/";
//		ResponseEntity<PlanetSearch> planet = restTemplate.getForEntity(URL, PlanetSearch.class);
//
//		return Arrays.asList(planet.getBody().getResults());
//	}
//
//	public SwApiPlanet getPlanetId(String id) {
//		if (!checkConnection()) {
//			throw new ConnectionException("Conection not detected");
//		}
//
//		String endpointString = "https://swapi.co/api/planets/" + id;
//		SwApiPlanet planetId = restTemplate.getForObject(endpointString, SwApiPlanet.class);
//
//		if (planetId == null) {
//			throw new PlanetNotFoundException("No planet on the Api! Check if 'id' is correct.");
//		}
//		return planetId;
//	}
//	
//	
//
//	public Planet getPlanetByName(String name) throws ConnectionException, PlanetNotFoundException {
//		if (!checkConnection()) {
//			throw new ConnectionException("Conection not detected");
//		}
//
//		String URL = SW_URL + "/planets?search=" + name;
//		Planet planetByName = restTemplate.getForObject(URL, Planet.class);
//
//		if (planetByName == null) {
//			throw new PlanetNotFoundException("No planet on the Api! Check if the 'name' is correct.");
//		}
//		return planetByName;
//
//	}

//	public int countFilmsByPlanet(String planetName) throws IOException {
//		if (!checkConnection())
//			throw new ConnectionException("Not connection detection");
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(SW_URL + "/planets?search=" + planetName,
//				HttpMethod.GET, entity, String.class);
//
//		JsonNode root = new ObjectMapper().readTree(response.getBody());
//
//		if (root != null && root.get("count").asLong() > 0) {
//			JsonNode results = root.get("results");
//			JsonNode planet = results.get(0);
//			JsonNode films = planet.get("films");
//			return films.size();
//		}
//
//		return 0;
//	}

}
