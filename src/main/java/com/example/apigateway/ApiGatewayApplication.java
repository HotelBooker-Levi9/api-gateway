package com.example.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	@Value("${user.url}")
	private String userUrl;

	@Value("${hotel.url}")
	private String hotelUrl;

	@Value("${cart.url}")
	private String cartUrl;

	@Value("${email.url}")
	private String emailUrl;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/clients/**")
						.uri(userUrl + "clients"))
						.build();
	}

	@Bean
	public RouteLocator hotelServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/hotels/**")
						.uri(hotelUrl + "hotels"))
				.route(r -> r.path("/cities/**")
						.uri(hotelUrl+"cities"))
				.route(r -> r.path("/destinations/**")
						.uri(hotelUrl+"destinations"))
				.build();
	}

	@Bean
	public RouteLocator cartServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/carts/**")
						.uri(cartUrl+"carts"))
				.route(r -> r.path("/reservations/**")
						.uri(cartUrl+"reservations"))
				.route(r -> r.path("/invoices/**")
						.uri(cartUrl+"invoices"))
				.build();
	}

	@Bean
	public RouteLocator emailServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/email/**")
						.uri(emailUrl+"sendConfirmationMail"))
				.build();
	}
}
