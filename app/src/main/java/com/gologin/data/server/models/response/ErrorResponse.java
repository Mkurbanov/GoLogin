package com.gologin.data.server.models.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("statusCode")
    int statusCode;
    @SerializedName("error")
    String error;
    @SerializedName("message")
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
