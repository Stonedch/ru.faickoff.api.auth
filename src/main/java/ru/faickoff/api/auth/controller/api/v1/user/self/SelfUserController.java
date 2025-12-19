package ru.faickoff.api.auth.controller.api.v1.user.self;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/self")
public class SelfUserController {

    @GetMapping
    public ResponseEntity<Void> getSelfUser() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PutMapping
    public ResponseEntity<Void> putSelfUser() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PatchMapping
    public ResponseEntity<Void> patchSelfUser() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSelfUser() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
