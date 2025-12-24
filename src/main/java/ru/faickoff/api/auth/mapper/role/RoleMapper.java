package ru.faickoff.api.auth.mapper.role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleListResponse;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleResponse;
import ru.faickoff.api.auth.dto.response.role.RoleResponse;
import ru.faickoff.api.auth.model.Role;

@Component
public class RoleMapper {

    public GatewayRoleResponse toGatewayRoleResponse(Role role) {
        return GatewayRoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .authority(role.getAuthority())
                .createdAt(role.getCreatedAt())
                .build();
    }

    public Set<GatewayRoleResponse> toGatewayRoleResponses(Set<Role> roles) {
        return roles.stream()
                .map(this::toGatewayRoleResponse)
                .collect(Collectors.toSet());
    }

    public List<GatewayRoleResponse> toGatewayRoleResponses(List<Role> roles) {
        return roles.stream()
                .map(this::toGatewayRoleResponse)
                .collect(Collectors.toList());
    }

    public GatewayRoleListResponse toGatewayRoleListResponse(List<Role> roles) {
        return GatewayRoleListResponse.builder()
                .roles(this.toGatewayRoleResponses(roles))
                .build();
    }

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .authority(role.getAuthority())
                .build();
    }

    public Set<RoleResponse> toRoleResponses(Set<Role> roles) {
        return roles.stream()
                .map(this::toRoleResponse)
                .collect(Collectors.toSet());
    }
}
