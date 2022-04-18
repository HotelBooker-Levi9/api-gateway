package com.example.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable().authorizeExchange()
                .pathMatchers("/hotels/**", "/clients/register").permitAll()
                .pathMatchers("/api/**").authenticated().and()
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
