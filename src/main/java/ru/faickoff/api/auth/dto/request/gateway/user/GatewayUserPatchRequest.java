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

    @Size(min = 8, max = 255, message = "The \"password\" field must be between 8 and 255 characters long")
    private String password;
}
