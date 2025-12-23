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
public class JwtRequest {

    @NotNull(message = "The \"username\" field cannot be null")
    private String username;

    @NotNull(message = "The \"password\" field cannot be null")
    private String password;
}
