package ru.otus.facadegateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
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
        return http
                .csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/*").permitAll()
                        .pathMatchers("/login").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .pathMatchers("/api/users/*").hasRole("ADMIN")
                        .pathMatchers("/createUser.html").permitAll()
                        .pathMatchers(HttpMethod.DELETE,"/api/authors/*").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/api/genres/*").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/api/books/*").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/api/comments/delete").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/logout").requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/logout"))
                .and()
                .build();
    }
}
