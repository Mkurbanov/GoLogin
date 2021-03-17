package com.gologin.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gologin.R;
import com.gologin.data.server.models.param.LoginModel;
import com.gologin.data.server.models.response.ErrorResponse;
import com.gologin.data.server.models.response.LoginResponse;
import com.gologin.utils.Functions;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gologin.utils.Functions.isEmailValid;
import static com.gologin.utils.Functions.isPasswordValid;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository = LoginRepository.getInstance();

    public MutableLiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public MutableLiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void loginDataChanged(String email, String password) {
        if (!isEmailValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    public void login(String email, String password) {
        loginRepository.login(new LoginModel(email, password, "what?"), new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Functions.logWrite("code = " + response.code());
                if (response.isSuccessful()) {
                    Functions.logWrite(response.body().getToken());
                    loginResult.setValue(new LoginResult(response.body()));
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        loginResult.setValue(new LoginResult(errorResponse));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Functions.logWriteError("ERROR?");
                loginResult.setValue(new LoginResult(Functions.getNetworkError()));
            }
        });
    }

}
