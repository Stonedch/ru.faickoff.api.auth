package ru.faickoff.api.auth.dto.request.gateway.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserCreateRequest {

    @NotNull(message = "The \"username\" field cannot be null")
    @Size(min = 8, max = 255, message = "The \"username\" field must be between 8 and 255 characters long")
    private String username;

    @NotNull(message = "The \"password\" field cannot be null")
    @Size(min = 8, max = 255, message = "The \"password\" field must be between 8 and 255 characters long")
    private String password;
}
