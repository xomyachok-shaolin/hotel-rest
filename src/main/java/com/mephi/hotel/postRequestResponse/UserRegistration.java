package com.mephi.hotel.postRequestResponse;

public class UserRegistration {
    private String last_name;
    private String first_name;
    private String email;
    private String login;
    private String password;
    private String phone_number;
    private String address;

    public String getLogin() {
        return login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLast_name() { return last_name; }

    public String getAddress() { return address; }

    public String getPhone_number() { return phone_number;  }
}

