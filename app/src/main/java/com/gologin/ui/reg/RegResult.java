package com.gologin.ui.reg;

import androidx.annotation.Nullable;

import com.gologin.data.server.models.response.ErrorResponse;
import com.gologin.data.server.models.response.LoginResponse;
import com.gologin.data.server.models.response.RegResponse;

public class RegResult {
    @Nullable
    private RegResponse regResponse;
    @Nullable
    private ErrorResponse errorResponse;
    @Nullable
    private String errorText;

    public RegResult(@Nullable RegResponse regResponse) {
        this.regResponse = regResponse;
    }

    public RegResult(@Nullable ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public RegResult(@Nullable String errorText) {
        this.errorText = errorText;
    }

    @Nullable
    public RegResponse getRegResponse() {
        return regResponse;
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
