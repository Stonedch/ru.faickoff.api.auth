package ru.faickoff.api.auth.controller.api.v1.auth.refresh;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/refresh")
public class AuthRefreshController {

    @PostMapping
    public ResponseEntity<Void> refresh() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
