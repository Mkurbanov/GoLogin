package com.gologin.ui.login;

import androidx.annotation.Nullable;

import com.gologin.App;
import com.gologin.data.server.models.param.LoginModel;
import com.gologin.data.server.models.response.LoginResponse;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static LoginRepository loginRepository;

    public static LoginRepository getInstance() {
        if (loginRepository == null)
            loginRepository = new LoginRepository();
        return loginRepository;
    }

    public void login(LoginModel loginModel, Callback<LoginResponse> callback){
        App.getInstance().getRequests().getApi().login(loginModel).enqueue(callback);
    }


}
