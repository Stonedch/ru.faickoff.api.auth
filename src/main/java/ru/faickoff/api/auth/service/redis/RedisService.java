package ru.faickoff.api.auth.service.redis;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService<K, V> {

    protected final RedisTemplate<K, V> redisTemplate;

    public void set(K key, V value, Duration timeout) {
        this.redisTemplate.opsForValue().set(key, value, timeout);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(this.redisTemplate.opsForValue().get(key));
    }
}
