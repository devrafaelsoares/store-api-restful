package br.devrafaelsoares.storeapirestful.infra.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static br.devrafaelsoares.storeapirestful.domain.user.Role.*;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            @NotNull HttpSecurity http
    ) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized -> authorized

                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register")
                            .hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login")
                            .permitAll()

                        .requestMatchers("/api/v1/category/**")
                            .hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/api/v1/categories")
                            .hasAnyRole(ADMIN.name(), SELLER.name(), CLIENT.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/product/**")
                            .hasAnyRole(ADMIN.name(), SELLER.name(), CLIENT.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/products")
                            .hasAnyRole(ADMIN.name(), SELLER.name(), CLIENT.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/product/**")
                            .hasAnyRole(ADMIN.name(), SELLER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/product/**")
                            .hasAnyRole(ADMIN.name(), SELLER.name())
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/product/**")
                            .hasAnyRole(ADMIN.name(), SELLER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/product/**")
                            .hasAnyRole(ADMIN.name(), SELLER.name())

                        .anyRequest()
                            .authenticated()
                )
                .build();
    }
}
