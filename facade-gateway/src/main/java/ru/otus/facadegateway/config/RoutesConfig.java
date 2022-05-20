package ru.otus.facadegateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.facadegateway.filter.UserHeaderFilter;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfig {
    private static final String LIBRARY_APPLICATION = "LIBRARY-MICROSERVICE";

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, UserHeaderFilter userFilter) {
        return builder.routes()
                .route(r -> r.path("/api/**")
                        .uri("lb://" + LIBRARY_APPLICATION))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> loginRouter(@Value("classpath:/public/login.html") Resource html) {
        return route(GET("/login"), request -> ok()
                .contentType(MediaType.TEXT_HTML)
                .bodyValue(html)
        );
    }
}
