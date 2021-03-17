package com.gologin.ui.login;

import androidx.annotation.Nullable;

import com.gologin.data.server.models.response.ErrorResponse;
import com.gologin.data.server.models.response.LoginResponse;

public class LoginResult {
    @Nullable
    private LoginResponse loginResponse;
    @Nullable
    private ErrorResponse errorResponse;
    @Nullable
    private String errorText;

    public LoginResult(@Nullable LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public LoginResult(@Nullable ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public LoginResult(@Nullable String errorText) {
        this.errorText = errorText;
    }

    @Nullable
    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    @Nullable
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    @Nullable
    public String getErrorText() {
        return errorText;
    }
}
