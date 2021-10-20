package br.idp.starwarsapi.starwarsAPI.config.security;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.idp.starwarsapi.starwarsAPI.model.UserPlanet;
import br.idp.starwarsapi.starwarsAPI.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserPlanet> userPlanet = userRepository.findByEmail(username);
		if(userPlanet.isPresent()) {
			return userPlanet.get();
		}
		throw new UsernameNotFoundException("Dados Inválidos");
	}

}
