package ru.otus.facadegateway.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;
import ru.otus.facadegateway.domain.User;

@ReactiveFeignClient(name = "user-microservice")
public interface UserMicroserviceClient {

    @GetMapping("/api/users/{name}")
    Mono<User> getUserByUsername(@PathVariable("name") String name);
}
