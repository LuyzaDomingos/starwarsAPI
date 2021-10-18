package br.idp.starwarsapi.starwarsAPI.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetInvalidAtribute;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;
import br.idp.starwarsapi.starwarsAPI.service.PlanetService;
import br.idp.starwarsapi.starwarsAPI.service.SwApiService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/planets")
@Slf4j
public class PlanetController {
	
	@Autowired
	private SwApiService swApiService;
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetService planetService;
	
	Logger log = LoggerFactory.getLogger(PlanetController.class);

	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(planetService.listAllPlanets());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listId(@PathVariable String id){
		return ResponseEntity.ok(planetService.listPlanetsId(id));
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<?> listName(@PathVariable String name){
		return ResponseEntity.ok(planetService.listPlanetsName(name));
	}
	
	@GetMapping("/swapi")
	public ResponseEntity<?> listAllPlanetsSwapi() throws ConnectionException{
		return ResponseEntity.ok(swApiService.getAllPlanets());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid Planet planet,
			UriComponentsBuilder uriComponentsBuilder) throws PlanetNotFoundException, PlanetInvalidAtribute, IOException{
		 Planet newPlanet = planetService.createPlanet(planet);
		 
		 URI uri = uriComponentsBuilder.path("/planets/{id}").buildAndExpand(newPlanet.getId()).toUri();
		 return ResponseEntity.created(uri).body(newPlanet);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		planetRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
}
