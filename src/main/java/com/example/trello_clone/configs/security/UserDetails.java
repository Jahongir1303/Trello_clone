package com.example.trello_clone.configs.security;



import com.example.trello_clone.domains.auth.AuthPermission;
import com.example.trello_clone.domains.auth.AuthRole;
import com.example.trello_clone.domains.auth.AuthUser;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@Builder
public record UserDetails(AuthUser authUser) implements org.springframework.security.core.userdetails.UserDetails {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (Objects.nonNull(authUser.getRoles())) {
            for (AuthRole role : authUser.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
                for (AuthPermission permission : role.getPermissions()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
                }
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return authUser().getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return authUser.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getIsActive();
    }

    public Long getId() {
        return authUser.getId();
    }
}
