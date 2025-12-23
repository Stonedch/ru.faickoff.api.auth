package ru.faickoff.api.auth.service.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import ru.faickoff.api.auth.exception.TokenValidationException;
import ru.faickoff.api.auth.model.User;

@Service
@Log4j2
public class JwtProvider {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;
    private final int jwtAccessTokenExpirationSeconds;
    private final int jwtRefreshTokenExpirationSeconds;

    public JwtProvider(
            @Value("${ru.faickoff.api.auth.jwt.secret.access}") String jwtAccessSecret,
            @Value("${ru.faickoff.api.auth.jwt.secret.refresh}") String jwtRefreshSecret,
            @Value("${ru.faickoff.api.auth.jwt.expiration.access}") int jwtAccessTokenExpirationSeconds,
            @Value("${ru.faickoff.api.auth.jwt.expiration.refresh}") int jwtRefreshTokenExpirationSeconds) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
        this.jwtAccessTokenExpirationSeconds = jwtAccessTokenExpirationSeconds;
        this.jwtRefreshTokenExpirationSeconds = jwtRefreshTokenExpirationSeconds;
    }

    public String generateAccessToken(@NonFinal User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusSeconds(this.jwtAccessTokenExpirationSeconds)
                .atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(accessExpiration)
                .signWith(this.jwtAccessSecret)
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .compact();
    }

    public String generateRefreshToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(this.jwtRefreshTokenExpirationSeconds)
                .atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) throws TokenValidationException {
        return this.validateToken(accessToken, this.jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) throws TokenValidationException {
        return this.validateToken(refreshToken, this.jwtRefreshSecret);
    }

    public Claims getAccessClaims(@NonNull String token) {
        return this.getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return this.getClaims(token, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull SecretKey secret) throws TokenValidationException {
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenValidationException("Token expired", e);
        } catch (UnsupportedJwtException e) {
            throw new TokenValidationException("Unsupported jwt", e);
        } catch (MalformedJwtException e) {
            throw new TokenValidationException("Malformed jwt", e);
        } catch (Exception e) {
            throw new TokenValidationException("invalid token", e);
        }
    }

    private Claims getClaims(@NonNull String token, @NonNull SecretKey secret) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
