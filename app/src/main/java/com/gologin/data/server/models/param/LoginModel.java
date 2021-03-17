package com.gologin.data.server.models.param;

public class LoginModel {
    String username;
    String password;
    Boolean fromApp = true;
    String googleClientId;

    public LoginModel(String username, String password, String googleClientId) {
        this.username = username;
        this.password = password;
        this.googleClientId = googleClientId;
    }
}
