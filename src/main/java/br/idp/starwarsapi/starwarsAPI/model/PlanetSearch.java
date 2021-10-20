package br.idp.starwarsapi.starwarsAPI.model;




public class PlanetSearch {

	private Integer count;
	private SwApiPlanet[] results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public SwApiPlanet[] getResults() {
		return results;
	}

	public void setResults(SwApiPlanet[] results) {
		this.results = results;
	}

}
