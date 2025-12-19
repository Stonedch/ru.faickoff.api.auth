package ru.faickoff.api.auth.controller.api.v1.auth.signin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/signin")
public class AuthSigninController {

    @PostMapping
    public ResponseEntity<Void> signin() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
