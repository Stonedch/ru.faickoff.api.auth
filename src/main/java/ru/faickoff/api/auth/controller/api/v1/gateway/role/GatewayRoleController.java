package ru.faickoff.api.auth.controller.api.v1.gateway.role;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleListResponse;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleResponse;
import ru.faickoff.api.auth.mapper.role.RoleMapper;
import ru.faickoff.api.auth.model.Role;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;
import ru.faickoff.api.auth.service.role.RoleService;

@RestController
@RequestMapping("/api/v1/gateway/roles")
@RequiredArgsConstructor
public class GatewayRoleController {

    private final LoggerHttpServletRequestService logger;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayRoleListResponse> getAll(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        List<Role> roles = this.roleService.getAll();
        GatewayRoleListResponse responseBody = this.roleMapper.toGatewayRoleListResponse(roles);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<GatewayRoleResponse> getById(
            HttpServletRequest servletRequest,
            @PathVariable Long id) {
        this.logger.info(servletRequest);
        Role role = this.roleService.getById(id);
        GatewayRoleResponse responseBody = this.roleMapper.toGatewayRoleResponse(role);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
