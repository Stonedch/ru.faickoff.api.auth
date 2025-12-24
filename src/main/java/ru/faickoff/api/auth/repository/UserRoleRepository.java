package ru.faickoff.api.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.faickoff.api.auth.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    public List<UserRole> findByUserIdIn(Iterable<Long> userIds);
}
