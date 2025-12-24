package ru.faickoff.api.auth.dto.response.gateway.role;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoleResponse {

    private Long id;

    private String name;

    private String authority;

    private Date createdAt;
}
