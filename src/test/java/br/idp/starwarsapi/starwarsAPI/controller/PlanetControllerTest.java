package br.idp.starwarsapi.starwarsAPI.controller;


import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;


import br.idp.starwarsapi.starwarsAPI.model.Planet;
import br.idp.starwarsapi.starwarsAPI.service.SwApiService;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PlanetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Planet mockPlanet;
	
	@MockBean
	private SwApiService swApiService;
	
	@BeforeEach
	public void setUp() {
		mockPlanet = new Planet();
		mockPlanet.setName("Sullust");
		mockPlanet.setClimate("superheated");
		mockPlanet.setTerrain("mountains, volcanoes, rocky deserts");
	}
	
	@Test
	public void creatingPlanetAndShouldReturn201() throws Exception{
		URI uri = new URI("/planets");
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(toJson(mockPlanet))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	
	}
	
	@Test
	public void creatingPlanetThatAlreadyExistingAndShouldReturnConflict409() throws Exception{
		URI uri = new URI("/planets");
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(toJson(mockPlanet))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isConflict());
	
	}
	
	@Test
	public void creatingEmptyPlanetAndShouldReturnBadRequest400() throws Exception{
		URI uri = new URI("/planets");
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content("")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	
	}
	
	
	
	@Test
	public void returnPlanetsByName() throws Exception{
		URI uri = new URI("/planets/name/Sullust");
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	
	@Test
	public void returnPlanetsByWrongName() throws Exception{
		URI uri = new URI("/planets/name/Socorro");
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
			private String toJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	



}
