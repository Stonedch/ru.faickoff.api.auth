package ru.faickoff.api.auth.dto.request.gateway.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserPatchRequest {

    @Size(min = 4, max = 255, message = "The \"username\" field must be between 4 and 255 characters long")
    private String username;

    @Size(min = 8, max = 255, message = "The \"password\" field must be between 8 and 255 characters long")
    private String password;
}
