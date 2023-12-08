package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String role;
    private String status;

    private Sekolah sekolah;

    private Collection<? extends GrantedAuthority> authorities;



    public UserDetailsImpl(Long id, String email, String password, String role, Sekolah sekolah ,String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sekolah = sekolah;
        this.status = status;

    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getSekolah(),
                user.getStatus()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

