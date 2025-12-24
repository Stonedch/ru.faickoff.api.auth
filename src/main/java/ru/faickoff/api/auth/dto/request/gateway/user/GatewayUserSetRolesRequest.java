package ru.faickoff.api.auth.dto.request.gateway.user;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserSetRolesRequest {

    @NotNull(message = "The \"roles\" field cannot be null")
    private List<Long> roles;
}
