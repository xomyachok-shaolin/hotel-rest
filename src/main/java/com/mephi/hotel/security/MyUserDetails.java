package com.mephi.hotel.security;

import com.mephi.hotel.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String first_name;
    private String last_name;
    private String password;
    private String login;
    private String address;
    private String phone_number;
    private String email;
    private Long idUser;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.idUser = user.getIdUser();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.address = user.getAddress();
        this.phone_number = user.getPhone_number();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.authorities = Arrays.stream(user.getRole().getName().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Long getIdUser() {
        return idUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return login; }

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
