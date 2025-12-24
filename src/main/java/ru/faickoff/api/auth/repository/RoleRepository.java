package ru.faickoff.api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.faickoff.api.auth.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
