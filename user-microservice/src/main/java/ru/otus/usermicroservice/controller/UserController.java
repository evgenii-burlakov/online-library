package ru.otus.usermicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.usermicroservice.dto.UserDto;
import ru.otus.usermicroservice.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/api/users/{name}")
    public UserDto getUserByName(@PathVariable("name") String name) {
        return userRepository.findByUsername(name)
                .map(UserDto::toDto)
                .orElse(null);
    }
}
