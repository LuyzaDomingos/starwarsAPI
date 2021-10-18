package br.idp.starwarsapi.starwarsAPI.model;

public class PlanetSearch {

	private Integer count;
	private Planet[] results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Planet[] getResults() {
		return results;
	}

	public void setResults(Planet[] results) {
		this.results = results;
	}

}
