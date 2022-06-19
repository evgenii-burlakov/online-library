package ru.otus.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.usermicroservice.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);
    Optional<User> findByUsername(String username);
}