package br.idp.starwarsapi.starwarsAPI.model;

public class SwApiPlanet {

	private String name;
	
	private String climate;
	
	private String terrain;
	
	private String[] residents;

	private String[] films;
	
	
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


	public String[] getFilms() {
		return films;
	}

	public void setFilms(String[] films) {
		this.films = films;
	}

	public String[] getResidents() {
		return residents;
	}

	public void setResidents(String[] residents) {
		this.residents = residents;
	}

	public Integer getFilmsCount() {
		if (this.films == null) {
			return 0;
		} else {
			return this.films.length;
		}
	}
	
	public Integer getResidentsCount() {
		if (this.residents == null) {
			return 0;
		} else {
			return this.residents.length;
		}
	}

}
