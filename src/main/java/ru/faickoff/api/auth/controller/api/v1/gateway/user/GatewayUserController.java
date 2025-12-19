package ru.faickoff.api.auth.controller.api.v1.gateway.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gateway/users")
public class GatewayUserController {

    @GetMapping
    public ResponseEntity<Void> getAll() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getById(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }
}
