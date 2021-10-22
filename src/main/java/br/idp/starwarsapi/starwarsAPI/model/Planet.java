package br.idp.starwarsapi.starwarsAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class the represent a Planet in Star Wars World
 * @author luyza.ellen
 *
 */

@Entity
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String climate;

	private String terrain;

	private Integer numberFilms;
	
	private Integer numberOfResidents; 

	public Planet() {
	}

	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getNumberFilms() {
		return numberFilms;
	}

	public void setNumberFilms(Integer numberFilms) {
		this.numberFilms = numberFilms;
	}

	public Integer getNumberOfResidents() {
		return numberOfResidents;
	}

	public void setNumberOfResidents(Integer numberOfResidents) {
		this.numberOfResidents = numberOfResidents;
	}
	
}
