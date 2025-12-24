package ru.faickoff.api.auth.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.model.User;

@Service
@RequiredArgsConstructor
public class SelfUserService {

    private final UserService userService;

    public User getSelfUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        return user;
    }

    public User putSelfUser(User updating) {
        User selfUser = this.getSelfUser();
        User updated = this.userService.putById(selfUser.getId(), updating);
        return updated;
    }

    public User patchSelfUser(User updating) {
        User selfUser = this.getSelfUser();
        User updated = this.userService.patchById(selfUser.getId(), updating);
        return updated;
    }

    public void deleteSelfUser() {
        User selfUser = this.getSelfUser();
        this.userService.deleteById(selfUser.getId());
    }
}
