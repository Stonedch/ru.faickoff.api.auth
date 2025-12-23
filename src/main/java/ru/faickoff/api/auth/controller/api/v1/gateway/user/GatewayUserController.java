package ru.faickoff.api.auth.controller.api.v1.gateway.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserCreateRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPatchRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPutRequest;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;
import ru.faickoff.api.auth.service.user.UserService;

@RestController
@RequestMapping("/api/v1/gateway/users")
@RequiredArgsConstructor
public class GatewayUserController {

    private final LoggerHttpServletRequestService logger;
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GatewayUserListResponse> getAll(
            HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        List<User> users = this.userService.getAll();
        GatewayUserListResponse mappedUsers = this.userMapper.toGatewayUserListResponse(users);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUsers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GatewayUserResponse> getById(
            HttpServletRequest servletRequest,
            @PathVariable Long id) {
        this.logger.info(servletRequest);
        User user = this.userService.getById(id);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GatewayUserResponse> create(
            HttpServletRequest servletRequest,
            @Valid @RequestBody GatewayUserCreateRequest request) {
        this.logger.info(servletRequest, request.toString());
        User creatingUser = this.userMapper.toUser(request);
        User createdUser = this.userService.create(creatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GatewayUserResponse> put(
            HttpServletRequest servletRequest,
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPutRequest request) {
        this.logger.info(servletRequest, request.toString());
        User updatingUser = this.userMapper.toUser(request);
        User updatedUser = this.userService.putById(id, updatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GatewayUserResponse> patch(
            HttpServletRequest servletRequest,
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPatchRequest request) {
        this.logger.info(servletRequest, request.toString());
        User updatingUser = this.userMapper.toUser(request);
        User updatedUser = this.userService.patchById(id, updatingUser);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteById(
            HttpServletRequest servletRequest,
            @PathVariable Long id) {
        this.logger.info(servletRequest);
        this.userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
