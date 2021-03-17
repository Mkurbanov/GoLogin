package com.gologin.ui.home;

import com.gologin.App;
import com.gologin.data.server.models.param.LoginModel;
import com.gologin.data.server.models.response.LoginResponse;
import com.gologin.data.server.models.response.UserResponse;

import retrofit2.Callback;

public class HomeRepository {
    private static HomeRepository homeRepository;

    public static HomeRepository getInstance() {
        if (homeRepository == null)
            homeRepository = new HomeRepository();
        return homeRepository;
    }

    public void getUser(Callback<UserResponse> callback){
        App.getInstance().getRequests().getApi().getUser().enqueue(callback);
    }


}
