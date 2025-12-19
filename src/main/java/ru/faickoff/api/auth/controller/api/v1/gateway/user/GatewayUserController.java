package ru.faickoff.api.auth.controller.api.v1.gateway.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserCreateRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPatchRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPutRequest;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;

@RestController
@RequestMapping("/api/v1/gateway/users")
public class GatewayUserController {

    @GetMapping
    public ResponseEntity<GatewayUserListResponse> getAll() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> getById(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public ResponseEntity<GatewayUserResponse> create(
            @Valid @RequestBody GatewayUserCreateRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> put(
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPutRequest request) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> patch(
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPatchRequest request) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }
}
