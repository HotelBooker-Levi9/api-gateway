package com.example.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {


    @Autowired
    private ReactiveClientRegistrationRepository clientRegistrationRepository;

    private static final String[] PROTECTED_URLS_PUT = {
            "/hotels/*",
            "/cities*",
            "/cities/*",
            "/destinations*",
            "/destinations/*",
    };

    private static final String[] UNPROTECTED_URLS_POST = {
            "/carts/unavailableHotelIdsForDateRange*",
            "/hotels/priceForReservation",
            "/email/sendConfirmationMail"

    };

    private static final String[] PROTECTED_URLS_POST = {
            "/hotels/addHotel",
            "/cities/addCity",
            "/destinations/addDestination",
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable().authorizeExchange()
                .pathMatchers("/auth/**", "/oauth2/**", "/login").permitAll()
                .pathMatchers(HttpMethod.GET,"/hotels/**", "/cities/*", "/destinations/*", "/reservations/**").permitAll()
                .pathMatchers(HttpMethod.POST,UNPROTECTED_URLS_POST).permitAll()
                .pathMatchers("/*", "/clients/**", "/email/**").authenticated()
                .pathMatchers(HttpMethod.GET, "/carts/*").authenticated()
                .pathMatchers(HttpMethod.PUT, PROTECTED_URLS_PUT).authenticated()
                .pathMatchers(HttpMethod.POST, PROTECTED_URLS_POST).authenticated()
                .and()
                .oauth2Login(oauth2 -> oauth2
                        .authorizationRequestResolver(
                                authorizationRequestResolver(this.clientRegistrationRepository)
                        ).and().formLogin().loginPage("/oauth2/authorization/api-client-oidc")
                )
                .oauth2Client(Customizer.withDefaults());
        return http.build();
    }

    private ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
            ReactiveClientRegistrationRepository clientRegistrationRepository) {

        return new DefaultServerOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository);
    }

}
