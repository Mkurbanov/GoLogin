package com.gologin.data.server.models.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("_id")
    String id;
    @SerializedName("access_token")
    String access_token;
    @SerializedName("refresh_token")
    String refresh_token;
    @SerializedName("token")
    String token;

    public String getId() {
        return id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getToken() {
        return token;
    }
}
