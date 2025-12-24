package ru.faickoff.api.auth.service.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.response.gateway.role.GatewayRoleResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserListResponse;
import ru.faickoff.api.auth.dto.response.gateway.user.GatewayUserResponse;
import ru.faickoff.api.auth.mapper.role.RoleMapper;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.Role;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.model.UserRole;
import ru.faickoff.api.auth.service.role.RoleService;
import ru.faickoff.api.auth.service.userrole.UserRoleService;

@Service
@RequiredArgsConstructor
public class GatewayUserResponseService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public GatewayUserListResponse getAll() {
        List<User> users = this.userService.getAll();
        List<GatewayUserResponse> mappedUsers = this.userMapper.toGatewayUserResponses(users);

        Set<Long> usersIdentifiers = users.stream().map(User::getId).collect(Collectors.toSet());
        List<UserRole> usersRoles = this.userRoleService.getByUserIdIn(usersIdentifiers);
        Set<Long> rolesIdentifiers = usersRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Role> roles = this.roleService.getAllById(rolesIdentifiers);

        mappedUsers = mappedUsers.stream().map((mappedUser) -> {
            Set<Long> userRolesIdentifiers = usersRoles.stream()
                    .filter((userRole) -> userRole.getUserId().equals(mappedUser.getId()))
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toSet());
            Set<Role> userRoles = roles.stream()
                    .filter((role) -> userRolesIdentifiers.contains(role.getId()))
                    .collect(Collectors.toSet());
            Set<GatewayRoleResponse> mappedUserRoles = this.roleMapper.toGatewayRoleResponses(userRoles);
            mappedUser.setRoles(mappedUserRoles);
            return mappedUser;
        }).collect(Collectors.toList());

        return GatewayUserListResponse.builder()
                .users(mappedUsers)
                .build();
    }

    public GatewayUserResponse getById(Long id) {
        User user = this.userService.getById(id);
        GatewayUserResponse mappedUser = this.userMapper.toGatewayUserResponse(user);
        mappedUser.setRoles(this.roleMapper.toGatewayRoleResponses(user.getRoles()));
        return mappedUser;
    }

    public GatewayUserResponse create(User creating) {
        User created = this.userService.create(creating);
        GatewayUserResponse mapped = this.userMapper.toGatewayUserResponse(created);
        mapped.setRoles(this.roleMapper.toGatewayRoleResponses(created.getRoles()));
        return mapped;
    }

    public GatewayUserResponse putById(Long id, User updating) {
        User updated = this.userService.putById(id, updating);
        GatewayUserResponse mapped = this.userMapper.toGatewayUserResponse(updated);
        mapped.setRoles(this.roleMapper.toGatewayRoleResponses(updated.getRoles()));
        return mapped;
    }

    public GatewayUserResponse patchById(Long id, User updating) {
        User updated = this.userService.patchById(id, updating);
        GatewayUserResponse mapped = this.userMapper.toGatewayUserResponse(updated);
        mapped.setRoles(this.roleMapper.toGatewayRoleResponses(updated.getRoles()));
        return mapped;
    }
}
