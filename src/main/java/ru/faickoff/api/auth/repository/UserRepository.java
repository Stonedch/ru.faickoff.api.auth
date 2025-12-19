package ru.faickoff.api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.faickoff.api.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsByUsername(String username);

    public Boolean existsByUsernameAndIdNot(String username, Long id);
}
