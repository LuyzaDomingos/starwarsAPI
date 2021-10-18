package br.idp.starwarsapi.starwarsAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlanetInvalidAtribute extends Exception{
	
	public PlanetInvalidAtribute(String message) {
		super (message);
	}

}
