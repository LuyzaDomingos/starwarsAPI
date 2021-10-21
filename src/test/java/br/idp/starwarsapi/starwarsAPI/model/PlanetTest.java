package br.idp.starwarsapi.starwarsAPI.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class PlanetTest {
	
	@Test
	public void creatingPlanet() {
		Planet planet_1  = new Planet("Sullust","superheated", "mountains, volcanoes, rocky deserts");
		Planet planet_2  = new Planet("Socorro","arid", "deserts, mountains");
		
		assertEquals(planet_1.getName(), "Sullust");
		assertEquals(planet_1.getClimate(), "superheated");
		assertEquals(planet_2.getName(), "Socorro");
		assertEquals(planet_2.getClimate(), "arid");
	}
	

	

}
