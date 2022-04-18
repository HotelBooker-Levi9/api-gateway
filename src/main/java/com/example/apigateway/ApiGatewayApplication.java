package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/clients/**")
						.uri("http://localhost:9000/clients"))
				.route(r -> r.path("/auth/**")
						.uri("http://localhost:9000/auth"))
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

	@Bean
	public RouteLocator cartServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/carts/**")
						.uri("http://localhost:8100/carts"))
				.route(r -> r.path("/reservations/**")
						.uri("http://localhost:8100/reservations"))
				.route(r -> r.path("/invoices/**")
						.uri("http://localhost:8100/invoices"))
				.build();
	}

	@Bean
	public RouteLocator emailServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/email/**")
						.uri("http://localhost:8300/sendConfirmationMail"))
				.build();
	}
}
