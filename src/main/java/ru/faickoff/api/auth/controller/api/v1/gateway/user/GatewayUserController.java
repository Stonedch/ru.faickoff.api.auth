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
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserSetRolesRequest;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.mapper.role.RoleMapper;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.Role;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;
import ru.faickoff.api.auth.service.user.GatewayUserResponseService;
import ru.faickoff.api.auth.service.user.UserRoleSetterService;
import ru.faickoff.api.auth.service.user.UserService;

@RestController
@RequestMapping("/api/v1/gateway/users")
@RequiredArgsConstructor
public class GatewayUserController {

    private final LoggerHttpServletRequestService logger;
    private final UserService userService;
    private final UserMapper userMapper;
    private final GatewayUserResponseService gatewayUserResponseService;
    private final UserRoleSetterService userRoleSetterService;
    private final RoleMapper roleMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayUserListResponse> getAll(
            HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        GatewayUserListResponse mappedUsers = this.gatewayUserResponseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(mappedUsers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayUserResponse> getById(
            HttpServletRequest servletRequest,
            @PathVariable Long id) {
        this.logger.info(servletRequest);
        GatewayUserResponse mappedUser = this.gatewayUserResponseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayUserResponse> create(
            HttpServletRequest servletRequest,
            @Valid @RequestBody GatewayUserCreateRequest request) {
        this.logger.info(servletRequest);
        User creatingUser = this.userMapper.toUser(request);
        GatewayUserResponse mappedUser = this.gatewayUserResponseService.create(creatingUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedUser);
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayRoleListResponse> setRolesByUserId(
            HttpServletRequest servletRequest,
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserSetRolesRequest request) {
        this.logger.info(servletRequest);
        List<Role> setted = this.userRoleSetterService.setRolesByUserId(id, request.getRoles());
        GatewayRoleListResponse mappedRoles = this.roleMapper.toGatewayRoleListResponse(setted);
        return ResponseEntity.status(HttpStatus.OK).body(mappedRoles);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayUserResponse> put(
            HttpServletRequest servletRequest,
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPutRequest request) {
        this.logger.info(servletRequest);
        User updatingUser = this.userMapper.toUser(request);
        GatewayUserResponse mappedUser = this.gatewayUserResponseService.putById(id, updatingUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayUserResponse> patch(
            HttpServletRequest servletRequest,
            @PathVariable Long id,
            @Valid @RequestBody GatewayUserPatchRequest request) {
        this.logger.info(servletRequest);
        User updatingUser = this.userMapper.toUser(request);
        GatewayUserResponse mappedUser = this.gatewayUserResponseService.patchById(id, updatingUser);
        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(
            HttpServletRequest servletRequest,
            @PathVariable Long id) {
        this.logger.info(servletRequest);
        this.userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
