package br.idp.starwarsapi.starwarsAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlanetDuplicateException extends RuntimeException {
	
	public PlanetDuplicateException(String message) {
		super(message);
	}

}
