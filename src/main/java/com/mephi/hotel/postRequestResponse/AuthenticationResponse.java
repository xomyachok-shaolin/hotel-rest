package com.mephi.hotel.postRequestResponse;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {

    private final Long idUser;
    private final String first_name;
    private final String last_name;
    private final String phone_number;
    private final String address;
    private final String login;
    private final String email;
    private final List<String> roles;
    private final String jwt;

    public AuthenticationResponse(Long idUser, String first_name, String last_name, String phone_number, String address,
                                  String login, String email, List<String> roles, String jwt) {
        this.idUser = idUser;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.login = login;
        this.email = email;
        this.roles = roles;
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getJwt() {
        return jwt;
    }

}
