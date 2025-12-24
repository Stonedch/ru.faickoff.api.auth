package ru.faickoff.api.auth.service.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.response.user.UserResponse;
import ru.faickoff.api.auth.mapper.role.RoleMapper;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.User;

@Service
@RequiredArgsConstructor
public class SelfUserResponseService {

    private final SelfUserService selfUserService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserResponse getSelfUser() {
        User selfUser = this.selfUserService.getSelfUser();
        UserResponse mappedUser = this.userMapper.toUserResponse(selfUser);
        mappedUser.setRoles(this.roleMapper.toRoleResponses(selfUser.getRoles()));
        return mappedUser;
    }

    public UserResponse putSelfUser(User updating) {
        User updated = this.selfUserService.putSelfUser(updating);
        UserResponse mappedUser = this.userMapper.toUserResponse(updated);
        mappedUser.setRoles(this.roleMapper.toRoleResponses(updated.getRoles()));
        return mappedUser;
    }

    public UserResponse patchSelfUser(User updating) {
        User updated = this.selfUserService.putSelfUser(updating);
        UserResponse mappedUser = this.userMapper.toUserResponse(updated);
        mappedUser.setRoles(this.roleMapper.toRoleResponses(updated.getRoles()));
        return mappedUser;
    }
}
