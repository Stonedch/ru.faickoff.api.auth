package ru.faickoff.api.auth.service.jwt;

import java.util.Optional;

public interface TokenStorage {

    public void set(String username, String token);

    public Optional<String> find(String username);
}
