package ru.faickoff.api.auth.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.service.user.UserService;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    
    public UserDetailsService getUserDetailsService(){
        return userService::getByUsername;
    }
}
