package ru.faickoff.api.auth.dto.response.gateway.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserResponse {

    private Long id;

    private String username;

    private String password;

    private Date createdAt;
}
