package ru.faickoff.api.auth.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserCreateRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPatchRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPutRequest;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.model.User;

@Component
public class UserMapper {

    public User toUser(GatewayUserCreateRequest gatewayUserCreateRequest) {
        return User.builder()
                .username(gatewayUserCreateRequest.getUsername())
                .password(gatewayUserCreateRequest.getPassword())
                .build();
    }

    public User toUser(GatewayUserPutRequest gatewayUserPutRequest) {
        return User.builder()
                .username(gatewayUserPutRequest.getUsername())
                .password(gatewayUserPutRequest.getPassword())
                .build();
    }

    public User toUser(GatewayUserPatchRequest gatewayUserPatchRequest) {
        return User.builder()
                .username(gatewayUserPatchRequest.getUsername())
                .password(gatewayUserPatchRequest.getPassword())
                .build();
    }

    public GatewayUserResponse toGatewayUserResponse(User user) {
        return GatewayUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public GatewayUserListResponse toGatewayUserListResponse(List<User> users) {
        return GatewayUserListResponse.builder()
                .users(users.stream()
                        .map(this::toGatewayUserResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
