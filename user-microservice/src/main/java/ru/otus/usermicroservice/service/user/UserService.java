package ru.otus.usermicroservice.service.user;

import ru.otus.usermicroservice.domain.User;

import java.util.Optional;

public interface UserService {
    User createWithUserRole(String name, String password);

    Optional<User> getByUserName(String userName);
}
