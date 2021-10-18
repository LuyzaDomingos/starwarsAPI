package br.idp.starwarsapi.starwarsAPI.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.idp.starwarsapi.starwarsAPI.exception.PlanetDuplicateException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetInvalidAtribute;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.model.SwApiPlanet;
import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;


@Service
public class PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private SwApiService swApiService;
	
	public List<Planet> listAllPlanets(){
		return planetRepository.findAll();
	}
	
	public Planet listPlanetsId(String id) {
		Planet planet = planetRepository.findById(id);
		if(planet == null) {
			throw new PlanetNotFoundException("Planet not found" + id);
		}
		return planet;	
	}
	
	public Planet listPlanetsName(String name) {
		Planet planet = planetRepository.findByName(name);
		if(planet == null) {
			throw new PlanetNotFoundException("Planet not found" + name);
		}
		return planet;	
	}
	
	public Planet createPlanet(Planet planetCreate) throws PlanetNotFoundException, PlanetInvalidAtribute, IOException {
		
		if(Stream.of(planetCreate,planetCreate.getNamePlanet()).anyMatch(Objects::isNull) ||planetCreate.getNamePlanet().contentEquals("")) {
			throw new PlanetInvalidAtribute("Planet not provided in the request");
		}
		
		if(planetRepository.findByName(planetCreate.getNamePlanet())!=null) {
			throw new PlanetDuplicateException("Planet name already exists.");
		}
		planetCreate.setNumberFilms(swApiService.countFilmsByPlanet(planetCreate.getNamePlanet()));
		return planetRepository.save(planetCreate);
		
	}
	
	public void deleteId(Long id) {
		planetRepository.deleteById(id);
	}
	
	
	

}
