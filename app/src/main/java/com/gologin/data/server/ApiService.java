package com.gologin.data.server;

import com.gologin.data.server.models.param.LoginModel;
import com.gologin.data.server.models.param.RegModel;
import com.gologin.data.server.models.response.LoginResponse;
import com.gologin.data.server.models.response.RegResponse;
import com.gologin.data.server.models.response.UserResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginModel loginModel);

    @POST("user")
    Call<RegResponse> reg(@Body RegModel regModel);

    @GET("user")
    Call<UserResponse> getUser();
}
