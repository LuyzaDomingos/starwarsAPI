package br.idp.starwarsapi.starwarsAPI.controller.form;

import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.repository.PlanetRepository;

public class UpdatePlanetForm {
	
	
	private String name;

	private String climate;

	private String terrain;

	
	public void setName(String name) {
		this.name = name;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	public Planet updatePlanet(Long id, PlanetRepository planetRepository) {
		@SuppressWarnings("deprecation")
		Planet planet  = planetRepository.getOne(id);
		planet.setName(name);
		planet.setClimate(climate);
		planet.setTerrain(terrain);
		return planet;
	}
	

}
