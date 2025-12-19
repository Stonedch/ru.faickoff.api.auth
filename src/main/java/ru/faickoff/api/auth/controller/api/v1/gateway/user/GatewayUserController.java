package ru.faickoff.api.auth.controller.api.v1.gateway.user;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserCreateRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPatchRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPutRequest;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.user.UserService;

@RestController
@RequestMapping("/api/v1/gateway/users")
@RequiredArgsConstructor
public class GatewayUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<GatewayUserListResponse> getAll() {
        List<User> users = this.userService.getAll();
        GatewayUserListResponse mappedUsers = this.userMapper.toGatewayUserListResponse(users);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> getById(@PathVariable Long id) {
        User user = this.userService.getById(id);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PostMapping
    public ResponseEntity<GatewayUserResponse> create(
            @Valid @RequestBody GatewayUserCreateRequest request) {
        User creatingUser = this.userMapper.toUser(request);
        User createdUser = this.userService.create(creatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> put(
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPutRequest request) {
        User updatingUser = this.userMapper.toUser(request);
        User updatedUser = this.userService.putById(id, updatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GatewayUserResponse> patch(
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPatchRequest request) {
        User updatingUser = this.userMapper.toUser(request);
        User updatedUser = this.userService.patchById(id, updatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
