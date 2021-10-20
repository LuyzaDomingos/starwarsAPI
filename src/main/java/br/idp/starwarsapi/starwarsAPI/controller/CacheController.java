package br.idp.starwarsapi.starwarsAPI.controller;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planetscache")
public class CacheController {

	Logger log = LoggerFactory.getLogger(PlanetController.class);

	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "getSwapiPlanetsName", allEntries = true),
			@CacheEvict(value = "getSwapiPlanetsId", allEntries = true) })
	public ResponseEntity<?> cacheClear() {
		log.info("Limpando cache....");
		return ResponseEntity.ok().build();

	}

}
