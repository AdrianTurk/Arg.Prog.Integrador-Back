package com.portfolio.backend.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MainUser implements UserDetails {

    private String name;
    private String userName;
    private String userEmail;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String name, String userName, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.userName = userName;
        this.userEmail = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
        return new MainUser(user.getName(), user.getUserName(), user.getEmail(), user.getPassword(), authorities);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return userEmail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
