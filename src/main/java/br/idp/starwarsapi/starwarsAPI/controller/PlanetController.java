package br.idp.starwarsapi.starwarsAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.h2.command.dml.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.idp.starwarsapi.starwarsAPI.config.validation.ErrorForm;
import br.idp.starwarsapi.starwarsAPI.controller.form.UpdatePlanetForm;
import br.idp.starwarsapi.starwarsAPI.exception.ConnectionException;

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
	@Cacheable(value = "listaDePlanetas")
	public ResponseEntity<?> listAll() throws ConnectionException, PlanetNotFoundException {
		log.info("Listando todos os planetas...");
		List<Planet> planets = planetRepository.findAll();
//		for(Planet planet:planets) {
//			SwApiPlanet swApiPlanet = swApiService.getSwapiPlanetsName(planet.getName());
//			planet.setNumberFilms(swApiPlanet.getFilmsCount());
//		}
//		
		return ResponseEntity.ok(planets);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> listId(@PathVariable long id) {
		log.info("Listando todos os planetas pelo id...");

		Planet planet = planetRepository.findById(id);
//		SwApiPlanet swApiPlanet = swApiService.getSwapiPlanetsName(planet.getName());
//		planet.setNumberFilms(swApiPlanet.getFilmsCount());

		if (planet == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorForm("id", "No planet was found with this id  = " + id));
		}

		return ResponseEntity.ok(planet);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> listName(@PathVariable String name) {
		log.info("Listando todos os planetas pelo nome...");
		Planet planet = planetRepository.findByName(name);

		if (planet == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorForm("id", "No planet was found with this name  = " + name));
		}

		return ResponseEntity.ok(planet);
	}
	
	
	@GetMapping("/swapi/{id}")
	public ResponseEntity<?> listSwapiPlanetsId(@PathVariable Long id) throws ConnectionException{
		return ResponseEntity.ok(swApiService.getSwapiPlanetsId(id));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePlanetas", allEntries = true)
	public ResponseEntity<?> create(@RequestBody @Valid Planet planet, UriComponentsBuilder uriComponentsBuilder) {
		log.info("criando um planeta...");

		if (Stream.of(planet, planet.getName()).anyMatch(Objects::isNull) || planet.getName().contentEquals("")
				|| planet.getClimate().contentEquals("") || planet.getTerrain().contentEquals("")) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorForm("name or climate or terrain", "Planet needs description"));
		}

		if (planetRepository.findByName(planet.getName()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ErrorForm("name", "Planet name already exists"));
		}

		planetRepository.save(planet);

		URI uri = uriComponentsBuilder.path("/planets/{id}")
				.buildAndExpand(planet.getId()).toUri();

		return ResponseEntity.created(uri).body(planet);
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePlanetas", allEntries = true)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UpdatePlanetForm updateForm){
		log.info("atualizando um planeta...");
		Optional<Planet> optional = planetRepository.findById(id);
		
		if(!optional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorForm("id", "The planet you are looking for doesn't exist"));
		}
		
//		if(optional.isEmpty()){
//			//Planet planet = updateForm.updatePlanet(id, planetRepository);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(new ErrorForm("name or climate or terrain", "Planet needs description"));
//		}
		
		Planet planet = updateForm.updatePlanet(id, planetRepository);
		return ResponseEntity.ok(planet);
			
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePlanetas", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("excuindo um planeta...");
		Optional<Planet> planet = planetRepository.findById(id);
		if (planet.isPresent()) {
			planetRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorForm("id", "Id dont exist"));
	}
	
	

}
