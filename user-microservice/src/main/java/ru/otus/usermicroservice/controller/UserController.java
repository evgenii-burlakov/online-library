package ru.otus.usermicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.usermicroservice.dto.UserDto;
import ru.otus.usermicroservice.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/{name}")
    public UserDto getUserByName(@PathVariable("name") String name) {
        return userService.getByUserName(name)
                .map(UserDto::toDto)
                .orElse(null);
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserDto> createUserWithUserRole(UserDto user) {
        userService.createWithUserRole(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
