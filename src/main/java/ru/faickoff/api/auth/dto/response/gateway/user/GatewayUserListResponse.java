package ru.faickoff.api.auth.dto.response.gateway.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUserListResponse {

    private List<GatewayUserResponse> users;
}
