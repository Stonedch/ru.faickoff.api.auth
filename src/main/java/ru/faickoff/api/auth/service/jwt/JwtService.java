package ru.faickoff.api.auth.service.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import ru.faickoff.api.auth.dto.request.jwt.JwtRequest;
import ru.faickoff.api.auth.dto.response.jwt.JwtResponse;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.user.UserService;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public JwtResponse signin(@NonFinal JwtRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        try {
            this.authenticationManager.authenticate(authenticationToken);
            final User user = this.userService.getByUsername(authRequest.getUsername());
            final String accessToken = this.jwtProvider.generateAccessToken(user);
            final String refreshToken = this.jwtProvider.generateRefreshToken(user);
            this.refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Incorrect username or password");
        }
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        jwtProvider.validateRefreshToken(refreshToken);

        final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        final String username = claims.getSubject();

        final String saveRefreshToken = refreshStorage.get(username);

        if (saveRefreshToken == null || !saveRefreshToken.equals(refreshToken)) {
            throw new IllegalArgumentException("invalid refresh token");
        }

        final User user = this.userService.getByUsername(username);

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String newRefreshToken = jwtProvider.generateRefreshToken(user);

        refreshStorage.put(user.getUsername(), newRefreshToken);

        return new JwtResponse(accessToken, newRefreshToken);
    }
}
