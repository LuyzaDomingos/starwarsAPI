package br.idp.starwarsapi.starwarsAPI.model;

public class SwApiPlanet {

	private String name;
	private String[] films;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getFilms() {
		return films;
	}

	public void setFilms(String[] films) {
		this.films = films;
	}
	
	public Integer getFilmsCount() {
		if(this.films==null) {
			return 0;
		}
		else {
			return this.films.length;
		}
	}

}
