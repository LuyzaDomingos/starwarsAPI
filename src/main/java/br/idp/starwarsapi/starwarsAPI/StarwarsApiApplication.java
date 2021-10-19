package br.idp.starwarsapi.starwarsAPI;

import java.io.IOException;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class StarwarsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApiApplication.class, args);
	}
	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		RestTemplate restTemplate = builder.build();
//		restTemplate.getInterceptors().add(addRequestHeader("User-agent", "curl/7.59.0"));
//		return restTemplate;
//	}
//	@Bean
//	public ClientHttpRequestInterceptor addRequestHeader(String name, String value) {
//		return new ClientHttpRequestInterceptor() {
//			@Override
//			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//					throws IOException {
//				request.getHeaders().set(name, value);
//				return execution.execute(request, body);
//			}
//		};
//	}

}
