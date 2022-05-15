package ru.otus.librarymicroservice.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.librarymicroservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}