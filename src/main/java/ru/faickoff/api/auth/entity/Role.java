package ru.faickoff.api.auth.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    private Integer value;

    public String getAuthority() {
        return this.value.toString();
    }
}
