package br.idp.starwarsapi.starwarsAPI.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.idp.starwarsapi.starwarsAPI.model.UserPlanet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${starwarsAPI.jwt.secret}")
	private String secret;

	@Value("${starwarsAPI.jwt.expiration}")
	private String expiration;

	public String generateToken(Authentication authentication) {
		UserPlanet logado = (UserPlanet) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiracion = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API do Star Wars").setSubject(logado.getId().toString()).setIssuedAt(today)
				.setExpiration(dateExpiracion).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
