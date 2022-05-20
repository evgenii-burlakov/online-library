package ru.otus.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.usermicroservice.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role save(Role role);
}