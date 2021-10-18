package br.idp.starwarsapi.starwarsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class StarwarsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApiApplication.class, args);
	}

}
