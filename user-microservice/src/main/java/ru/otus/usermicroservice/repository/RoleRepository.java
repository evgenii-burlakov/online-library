package ru.otus.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.usermicroservice.domain.Role;
import ru.otus.usermicroservice.domain.RoleId;

public interface RoleRepository extends JpaRepository<Role, RoleId> {

    Role save(Role role);
}