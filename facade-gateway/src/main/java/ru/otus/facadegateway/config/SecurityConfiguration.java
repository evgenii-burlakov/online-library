package ru.otus.facadegateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ru.otus.facadegateway.service.UserDetailsSecurityService;

@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsSecurityService userDetailsSecurityService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return (username) -> userDetailsSecurityService.loadUserByUsername(username);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()

                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/*")
                        .permitAll()
                        .pathMatchers("/api/authors/*").hasRole("ADMIN")
                        .pathMatchers("/api/genres/*").hasRole("ADMIN")
                        .pathMatchers("/api/books/delete").hasRole("ADMIN")
                        .pathMatchers("/api/comments/delete").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .formLogin();
        return http.build();
    }
}
