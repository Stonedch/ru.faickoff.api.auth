package ru.faickoff.api.auth.dto.request.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshRequest {

    @NotNull(message = "The \"refreshToken\" field cannot be null")
    private String refreshToken;
}
