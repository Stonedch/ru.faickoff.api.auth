package ru.faickoff.api.auth.dto.response.gateway.user;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserResponse {

    private Long id;

    private String username;

    private String password;

    private Set<GatewayRoleResponse> roles;

    private Date createdAt;
}
