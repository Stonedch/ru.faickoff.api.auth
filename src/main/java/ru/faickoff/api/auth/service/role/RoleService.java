package ru.faickoff.api.auth.service.role;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.model.Role;
import ru.faickoff.api.auth.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }

    public List<Role> getAllById(Iterable<Long> ids) {
        return this.roleRepository.findAllById(ids);
    }

    public Role getById(Long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role by current identifier not found"));
    }
}
