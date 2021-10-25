package br.idp.starwarsapi.starwarsAPI.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.h2.value.ValueJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.idp.starwarsapi.starwarsAPI.config.validation.ErrorForm;
import br.idp.starwarsapi.starwarsAPI.controller.PlanetController;
import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.model.PlanetSearch;
import br.idp.starwarsapi.starwarsAPI.model.SwApiPlanet;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SwApiService {

	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	Logger log = LoggerFactory.getLogger(PlanetController.class);

	@Value("${swapi.SW_URL}")
	private String sw_url;
	
	@Value("${swapi.SW_URL_NAME}")
	private String sw_url_name;
	
	@Value("${swapi.BASE_URL}")
	private String sw_base;
	
	@Value("${url.headers.value}")
	private String headerValue;
	
	@Value("${url.headers.name}")
	private String headerName;
	
	
	@Cacheable(value = "getSwapiPlanetsId")
	public SwApiPlanet getSwapiPlanetsId(Long id) throws ConnectionException, PlanetNotFoundException {
		log.info("Acessando api para buscar planetas pelo id...");

		if (!checkConnection()) 
			throw new ConnectionException("Not connection detection");
	

		if (id <= 60) {
			String swUrl = sw_url + id;

			return restTemplate.getForObject(swUrl, SwApiPlanet.class);

		}
		throw new PlanetNotFoundException("Planet dont exist in the API!!");

	}
	
	
	public int countFilmsByPlanet(String planetName) throws IOException {
		JsonNode planetJsonNode;
		planetJsonNode = counting(planetName);
		JsonNode  films = planetJsonNode.get("films");
		return films.size();
	}
	
	public int countResidentsByPlanet(String planetName) throws IOException {
		JsonNode planetJsonNode;
		planetJsonNode = counting(planetName);
		JsonNode residents = planetJsonNode.get("residents");
		return residents.size();
	}
	
	
	public JsonNode counting(String planetName) throws IOException {
		if (!checkConnection())
			throw new ConnectionException("Not connection detection");

		
		headers.add(headerName,headerValue);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<String> response = restTemplate.exchange(sw_url_name + planetName,
				HttpMethod.GET, entity, String.class);

		JsonNode root = new ObjectMapper().readTree(response.getBody());

		if (root != null && root.get("count").asLong() > 0) {
			JsonNode results = root.get("results");
			JsonNode planet = results.get(0);
			return planet;
		}
		
		throw new PlanetNotFoundException("Planet dont exist in SW API.....");

	}
	
	
	private boolean checkConnection() {
		try {
			URL url = new URL(sw_base);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
