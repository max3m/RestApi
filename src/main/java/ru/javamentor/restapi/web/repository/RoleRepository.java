package ru.javamentor.restapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.restapi.web.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(String roleName);
}