package ru.faickoff.api.auth.service.jwt;

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
import ru.faickoff.api.auth.exception.TokenValidationException;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.user.UserService;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshStorage refreshStorage;

    public JwtResponse signin(@NonFinal JwtRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword());

            this.authenticationManager.authenticate(authenticationToken);

            final User user = this.userService.getByUsername(authRequest.getUsername());

            final String accessToken = this.jwtProvider.generateAccessToken(user);
            final String refreshToken = this.jwtProvider.generateRefreshToken(user);

            this.refreshStorage.set(user.getUsername(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Incorrect username or password");
        }
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        try {
            jwtProvider.validateRefreshToken(refreshToken);

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();

            String saveRefreshToken = this.refreshStorage.find(username)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

            if (!saveRefreshToken.equals(refreshToken)) {
                throw new IllegalArgumentException("Invalid refresh token");
            }

            final User user = this.userService.getByUsername(username);

            final String accessToken = jwtProvider.generateAccessToken(user);
            final String newRefreshToken = jwtProvider.generateRefreshToken(user);

            this.refreshStorage.set(user.getUsername(), newRefreshToken);

            return new JwtResponse(accessToken, newRefreshToken);
        } catch (TokenValidationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
