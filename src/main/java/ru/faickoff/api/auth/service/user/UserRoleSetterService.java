package ru.faickoff.api.auth.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.model.Role;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.role.RoleService;

@Service
@RequiredArgsConstructor
public class UserRoleSetterService {

    private final UserService userService;
    private final RoleService roleService;

    public List<Role> setRolesByUserId(Long userId, List<Long> rolesIdentifiers) {
        User user = this.userService.getById(userId);
        List<Role> roles = this.roleService.getAllById(rolesIdentifiers);
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        this.userService.save(user);
        return roles;
    }
}
