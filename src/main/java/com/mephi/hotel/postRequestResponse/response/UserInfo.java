package com.mephi.hotel.postRequestResponse.response;

import com.mephi.hotel.model.Role;

public class UserInfo {
    private Long idUser;

    private String last_name;

    private String first_name;

    private String address;

    private String phone_number;

    private String email;

    private String login;

    private String password;

    private Role role;

    public UserInfo(Long idUser, String last_name, String first_name, String address, String phone_number, String email, String login, String password, Role role) {
        this.idUser = idUser;
        this.last_name = last_name;
        this.first_name = first_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
