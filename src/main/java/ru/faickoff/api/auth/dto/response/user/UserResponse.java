package ru.faickoff.api.auth.dto.response.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.faickoff.api.auth.dto.response.role.RoleResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private Set<RoleResponse> roles;
}
