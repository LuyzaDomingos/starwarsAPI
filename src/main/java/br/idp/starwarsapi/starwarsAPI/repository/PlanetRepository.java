package br.idp.starwarsapi.starwarsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.idp.starwarsapi.starwarsAPI.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

	Planet findById(long id);

	Planet findByName(String name);

}
