package br.idp.starwarsapi.starwarsAPI.controller.dto;

public class PlanetAllDto {

	private int id;

	private String name;

	private String climate;

	private String terrain;

	public PlanetAllDto(int id, String name, String climate, String terrain) {
		super();
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	
}
