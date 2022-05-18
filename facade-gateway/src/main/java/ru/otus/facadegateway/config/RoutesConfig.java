package ru.otus.facadegateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@Configuration
public class RoutesConfig {
    private static final String LIBRARY_APPLICATION = "LIBRARY-MICROSERVICE";

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/**")
                        .filters(f -> f.addRequestHeader("User",
                                Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Principal::getName).orElse("")))
                        .uri("lb://" + LIBRARY_APPLICATION))
                .build();
    }
}
