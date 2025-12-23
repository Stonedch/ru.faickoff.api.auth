package ru.faickoff.api.auth.controller.api.v1.auth.signin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.request.jwt.JwtRequest;
import ru.faickoff.api.auth.dto.response.jwt.JwtResponse;
import ru.faickoff.api.auth.service.jwt.JwtService;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;

@RestController
@RequestMapping("/api/v1/auth/signin")
@RequiredArgsConstructor
public class AuthSigninController {

    private final LoggerHttpServletRequestService logger;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<JwtResponse> signin(HttpServletRequest servletRequest, @Valid @RequestBody JwtRequest request) {
        this.logger.info(servletRequest);
        JwtResponse responseBody = this.jwtService.signin(request);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
