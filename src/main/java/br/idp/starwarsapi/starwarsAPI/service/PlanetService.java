package br.idp.starwarsapi.starwarsAPI.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.idp.starwarsapi.starwarsAPI.exception.PlanetDuplicateException;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetInvalidAtribute;
import br.idp.starwarsapi.starwarsAPI.exception.PlanetNotFoundException;
import br.idp.starwarsapi.starwarsAPI.model.Planet;

import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private SwApiService swApiService;

//	public List<Planet> listAllPlanets() {
//		return planetRepository.findAll();
//	}

//	public Planet listPlanetsId(long id) {
//		Planet planet = planetRepository.findById(id);
//		return planet;
//	}
//
//	public Planet listPlanetsName(String name) {
//		Planet planet = planetRepository.findByName(name);
//		return planet;
//	}

//	public Planet createPlanet(Planet planetCreate) throws PlanetNotFoundException, PlanetInvalidAtribute, IOException {
//
//		if (Stream.of(planetCreate, planetCreate.getName()).anyMatch(Objects::isNull)
//				|| planetCreate.getName().contentEquals("")) {
//			throw new PlanetInvalidAtribute("Planet not provided in the request");
//		}
//
//		if (planetRepository.findByName(planetCreate.getName()) != null) {
//			throw new PlanetDuplicateException("Planet name already exists.");
//		}
//		return planetRepository.save(planetCreate);
//
//	}

//	public void deleteId(Long id) {
//		planetRepository.deleteById(id);
//	}

}
