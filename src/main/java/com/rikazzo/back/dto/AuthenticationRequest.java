package com.rikazzo.back.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class AuthenticationRequest implements UserDetails {

    private String username;
    private String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean accountExpired;
    private final boolean accountLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked;
    }
}
