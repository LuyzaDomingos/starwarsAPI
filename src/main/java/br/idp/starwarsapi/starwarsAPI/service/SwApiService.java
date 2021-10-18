package br.idp.starwarsapi.starwarsAPI.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.model.PlanetSearch;
import br.idp.starwarsapi.starwarsAPI.model.SwApiPlanet;

@Service
public class SwApiService {

//	@Autowired
	private RestTemplate restTemplate;

	private String SW_URL = "https://swapi.co/api";

	public List<Planet> getAllPlanets() throws ConnectionException {
		if (!checkConnection()) {
			throw new ConnectionException("Conection not detected");
		}

		String URL = SW_URL + "/planets/";
		ResponseEntity<PlanetSearch> planet = restTemplate.getForEntity(URL, PlanetSearch.class);

		return Arrays.asList(planet.getBody().getResults());
	}

	public Planet getPlanetById(String id) throws ConnectionException, PlanetNotFoundException {
		if (!checkConnection()) {
			throw new ConnectionException("Conection not detected");
		}

		String URL = SW_URL + "/planets/" + id;
		Planet planetById = restTemplate.getForObject(URL, Planet.class);

		if (planetById == null) {
			throw new PlanetNotFoundException("No planet on the Api! Check if 'id' is correct.");
		}
		return planetById;

	}

	public Planet getPlanetByName(String name) throws ConnectionException, PlanetNotFoundException {
		if (!checkConnection()) {
			throw new ConnectionException("Conection not detected");
		}

		String URL = SW_URL + "/planets?search=" + name;
		Planet planetByName = restTemplate.getForObject(URL, Planet.class);

		if (planetByName == null) {
			throw new PlanetNotFoundException("No planet on the Api! Check if the 'name' is correct.");
		}
		return planetByName;

	}
	
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
	
	
	

	private boolean checkConnection() {
		try {
			URL url = new URL(SW_URL);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
