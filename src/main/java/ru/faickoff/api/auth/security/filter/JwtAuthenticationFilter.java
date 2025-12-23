package ru.faickoff.api.auth.security.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.faickoff.api.auth.exception.TokenValidationException;
import ru.faickoff.api.auth.service.jwt.JwtProvider;
import ru.faickoff.api.auth.service.security.SecurityService;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer";
    public static final String HEADER_NAME = "Authorization";

    private final JwtProvider jwtProvider;
    private final SecurityService securityService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> findedAccessToken = this.findTokenFromRequest(request);

            if (findedAccessToken.isPresent()) {
                String accessToken = findedAccessToken.get();
                if (this.jwtProvider.validateAccessToken(accessToken)) {
                    String username = this.jwtProvider.getAccessClaims(accessToken).getSubject();
                    UserDetails userDetails = this.securityService.getUserDetailsService().loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                }
            }
        } catch (UsernameNotFoundException | TokenValidationException e) {
            JwtAuthenticationFilter.log.debug(e.getMessage(), e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private Optional<String> findTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtAuthenticationFilter.HEADER_NAME);

        if (authHeader == null
                || authHeader.isEmpty()
                || !authHeader.startsWith(JwtAuthenticationFilter.BEARER_PREFIX)) {
            return Optional.ofNullable(null);
        }

        return Optional.of(authHeader.substring(JwtAuthenticationFilter.BEARER_PREFIX.length()).trim());
    }
}
