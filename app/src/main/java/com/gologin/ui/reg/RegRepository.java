package com.gologin.ui.reg;

import com.gologin.App;
import com.gologin.data.server.models.param.RegModel;
import com.gologin.data.server.models.response.RegResponse;

import retrofit2.Callback;

public class RegRepository {
    private static RegRepository regRepository;

    public static RegRepository getInstance() {
        if (regRepository == null)
            regRepository = new RegRepository();
        return regRepository;
    }

    public void reg(RegModel regModel, Callback<RegResponse> callback){
        App.getInstance().getRequests().getApi().reg(regModel).enqueue(callback);
    }


}
