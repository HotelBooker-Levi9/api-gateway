package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/clients/**")
						.uri("http://localhost:8000/clients"))
						.build();
	}

	@Bean
	public RouteLocator hotelServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/hotels/**")
						.uri("http://localhost:8200/hotels"))
				.route(r -> r.path("/cities/**")
						.uri("http://localhost:8200/cities"))
				.route(r -> r.path("/destinations/**")
						.uri("http://localhost:8200/destinations"))
				.build();
	}
}
