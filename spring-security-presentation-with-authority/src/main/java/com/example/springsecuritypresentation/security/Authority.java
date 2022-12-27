package com.example.springsecuritypresentation.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
@Getter
public enum Authority implements GrantedAuthority {
    USER,
    ADMIN,
    EDITOR,
    READONLY;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
