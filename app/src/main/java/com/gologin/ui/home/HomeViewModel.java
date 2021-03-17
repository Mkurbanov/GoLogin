package com.gologin.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gologin.data.server.models.response.UserResponse;
import com.gologin.utils.Functions;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    HomeRepository homeRepository = HomeRepository.getInstance();
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        loadData();
    }

    public LiveData<String> getText() {
        return mText;
    }

    private void loadData() {
        homeRepository.getUser(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mText.setValue(response.body().getEmail() + "\n" + response.body().getId());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}