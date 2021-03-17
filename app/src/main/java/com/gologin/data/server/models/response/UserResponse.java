package com.gologin.data.server.models.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("_id")
    String id;
    @SerializedName("email")
    String email;
    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("plan")
    JsonObject plan;
    @SerializedName("needCard")
    Boolean needCard;
    @SerializedName("hasTrial")
    Boolean hasTrial;
    @SerializedName("trialDays")
    int trialDays;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public JsonObject getPlan() {
        return plan;
    }

    public Boolean getNeedCard() {
        return needCard;
    }

    public Boolean getHasTrial() {
        return hasTrial;
    }

    public int getTrialDays() {
        return trialDays;
    }
}
