package ru.faickoff.api.auth.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserCreateRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPatchRequest;
import ru.faickoff.api.auth.dto.request.gateway.user.GatewayUserPutRequest;
import ru.faickoff.api.auth.dto.request.user.UserPatchRequest;
import ru.faickoff.api.auth.dto.request.user.UserPutRequest;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.dto.response.user.UserListResponse;
import ru.faickoff.api.auth.dto.response.user.UserResponse;
import ru.faickoff.api.auth.model.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toUser(GatewayUserCreateRequest gatewayUserCreateRequest) {
        return User.builder()
                .username(gatewayUserCreateRequest.getUsername())
                .password(gatewayUserCreateRequest.getPassword())
                .build();
    }

    public User toUser(GatewayUserPutRequest gatewayUserPutRequest) {
        return User.builder()
                .password(gatewayUserPutRequest.getPassword())
                .build();
    }

    public User toUser(GatewayUserPatchRequest gatewayUserPatchRequest) {
        return User.builder()
                .password(gatewayUserPatchRequest.getPassword())
                .build();
    }

    public User toUser(UserPutRequest userPutRequest) {
        return User.builder()
                .password(userPutRequest.getPassword())
                .build();
    }

    public User toUser(UserPatchRequest userPatchRequest) {
        return User.builder()
                .password(userPatchRequest.getPassword())
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

    public List<GatewayUserResponse> toGatewayUserResponses(List<User> users) {
        return users.stream()
                .map(this::toGatewayUserResponse)
                .collect(Collectors.toList());
    }

    public GatewayUserListResponse toGatewayUserListResponse(List<User> users) {
        return GatewayUserListResponse.builder()
                .users(this.toGatewayUserResponses(users))
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public UserListResponse toUserResponse(List<User> users) {
        return UserListResponse.builder()
                .users(users.stream()
                        .map(this::toUserResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
