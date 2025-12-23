package ru.faickoff.api.auth.service.jwt;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.service.redis.RedisService;

@Service
@RequiredArgsConstructor
public class RefreshStorage implements TokenStorage {

    private final RedisService<String, String> redisService;

    @Value("${ru.faickoff.api.auth.jwt.expiration.refresh}")
    private int jwtRefreshTokenExpirationSeconds;
    @Value("${ru.faickoff.api.auth.jwt.storage.prefix.refresh}")
    private String prefix;

    public void set(String username, String token) {
        this.redisService.set(
                this.getKey(username),
                token,
                Duration.ofSeconds(this.jwtRefreshTokenExpirationSeconds));
    }

    public Optional<String> find(String username) {
        return this.redisService.get(this.getKey(username));
    }

    private String getKey(String username) {
        return this.prefix + username;
    }
}
