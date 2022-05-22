package ru.otus.facadegateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.facadegateway.domain.Role;
import ru.otus.facadegateway.client.UserMicroserviceClient;

import static org.springframework.security.core.userdetails.User.withUsername;


@Service
public class UserDetailsSecurityService {

    @Autowired
    private  UserMicroserviceClient userMicroserviceClient;

    public Mono<UserDetails> loadUserByUsername(String username) {
        return userMicroserviceClient.getUserByUsername(username)
                .map(user -> {
                    if (user != null) {
                        User.UserBuilder builder = withUsername(username);
                        builder.password(user.getPassword());
                        builder.roles(user.getRoles().stream()
                                .map(Role::getRole).toArray(String[]::new));
                        return builder.build();
                    } else {
                        throw new UsernameNotFoundException("User not found.");
                    }
                });
    }
}
