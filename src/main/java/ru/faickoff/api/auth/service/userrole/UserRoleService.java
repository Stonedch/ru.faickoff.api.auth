package ru.faickoff.api.auth.service.userrole;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.model.UserRole;
import ru.faickoff.api.auth.repository.UserRoleRepository;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public List<UserRole> getByUserIdIn(Iterable<Long> userIds) {
        return this.userRoleRepository.findByUserIdIn(userIds);
    }
}
