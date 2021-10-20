package br.idp.starwarsapi.starwarsAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.idp.starwarsapi.starwarsAPI.model.UserPlanet;

public interface UserRepository extends JpaRepository<UserPlanet,Long>{
	
	Optional<UserPlanet> findByEmail(String email);

}
