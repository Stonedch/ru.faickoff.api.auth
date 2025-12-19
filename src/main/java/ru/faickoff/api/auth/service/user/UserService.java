package ru.faickoff.api.auth.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found by current identifier"));
    }

    public User save(User saving) {
        if (saving.getUsername() == null || saving.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (saving.getPassword() == null || saving.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        return this.userRepository.save(saving);
    }

    public User create(User creating) {
        if (this.userRepository.existsByUsername(creating.getUsername())) {
            throw new IllegalArgumentException("A user with this username has already been created");

        }

        User created = this.save(creating);

        return created;
    }

    public User put(User updating) {
        User user = this.getById(updating.getId());

        if (this.userRepository.existsByUsernameAndIdNot(updating.getUsername(), user.getId())) {
            throw new IllegalArgumentException("A user with this username has already been created");
        }

        user.setUsername(updating.getUsername());
        user.setPassword(updating.getPassword());

        User updated = this.save(user);

        return updated;
    }

    public User putById(Long id, User updating) {
        updating.setId(id);
        return this.put(updating);
    }

    public User patch(User updating) {
        User user = this.getById(updating.getId());

        if (this.userRepository.existsByUsernameAndIdNot(updating.getUsername(), user.getId())) {
            throw new IllegalArgumentException("A user with this username has already been created");
        }

        Optional.ofNullable(updating.getUsername())
            .ifPresent(username -> user.setUsername(updating.getUsername()));
        Optional.ofNullable(updating.getPassword())
            .ifPresent(password -> user.setPassword(updating.getPassword()));

        User updated = this.save(user);

        return updated;
    }

    public User patchById(Long id, User updating) {
        updating.setId(id);
        return this.patch(updating);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
