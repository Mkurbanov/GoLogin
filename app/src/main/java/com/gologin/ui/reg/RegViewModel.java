package com.gologin.ui.reg;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gologin.R;
import com.gologin.data.server.models.param.RegModel;
import com.gologin.data.server.models.response.ErrorResponse;
import com.gologin.data.server.models.response.RegResponse;
import com.gologin.utils.Functions;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gologin.utils.Functions.isEmailValid;
import static com.gologin.utils.Functions.isPasswordValid;

public class RegViewModel extends ViewModel {
    private MutableLiveData<RegFormState> regFormState = new MutableLiveData<>();
    private MutableLiveData<RegResult> regResult = new MutableLiveData<>();
    private RegRepository regRepository = RegRepository.getInstance();

    public MutableLiveData<RegFormState> getRegFormState() {
        return regFormState;
    }

    public MutableLiveData<RegResult> getRegResult() {
        return regResult;
    }

    public void regDataChanged(String email, String password, String confirmPassword) {
        if (!isEmailValid(email)) {
            regFormState.setValue(new RegFormState(R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            regFormState.setValue(new RegFormState(null, R.string.invalid_password, null));
        } else if (!password.equals(confirmPassword)){
            regFormState.setValue(new RegFormState(null, null, R.string.invalid_confirm_password));
        } else {
            regFormState.setValue(new RegFormState(true));
        }
    }

    public void reg(String email, String password, String confirmPassword) {
        regRepository.reg(new RegModel(email, password, confirmPassword, "what?"), new Callback<RegResponse>() {
            @Override
            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                Functions.logWrite("code = " + response.code());
                if (response.isSuccessful()) {
                    Functions.logWrite(response.body().getToken());
                    regResult.setValue(new RegResult(response.body()));
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        regResult.setValue(new RegResult(errorResponse));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegResponse> call, Throwable t) {
                Functions.logWriteError("ERROR?");
                regResult.setValue(new RegResult(Functions.getNetworkError()));
            }
        });
    }

}
