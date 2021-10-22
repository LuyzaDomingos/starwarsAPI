package br.idp.starwarsapi.starwarsAPI.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.idp.starwarsapi.starwarsAPI.repository.UserRepository;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// configuração da autenticação(login, acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());

	}

	// configuração de autorizaçao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/planets").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/*").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/id/*").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/name/*").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/swapi").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/swapi/*").permitAll()
				.antMatchers(HttpMethod.GET, "/planets/swapi/name/*").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository),
						UsernamePasswordAuthenticationFilter.class);

	}

	// configuração de recursos estaticos(js,css,img....)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");

	}

////	 gerar senha codificada
//		public static void main(String[] args) {
//			System.out.println(new BCryptPasswordEncoder().encode("1"));
//		}

}
