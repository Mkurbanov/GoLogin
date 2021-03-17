package com.gologin.data.server.models.param;

import com.google.gson.annotations.SerializedName;

public class RegModel {
    String email;
    String password;
    String passwordConfirm;
    String googleClientId;

    public RegModel(String email, String password, String passwordConfirm, String googleClientId) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.googleClientId = googleClientId;
    }
}
