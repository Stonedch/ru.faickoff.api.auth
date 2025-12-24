package ru.faickoff.api.auth.dto.request.user;

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
public class UserPutRequest {

    @NotNull(message = "The \"password\" field cannot be null")
    @Size(min = 8, max = 255, message = "The \"password\" field must be between 8 and 255 characters long")
    private String password;
}
