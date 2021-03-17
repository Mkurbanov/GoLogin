package com.gologin.data.server;


import com.gologin.App;
import com.gologin.config;
import com.gologin.utils.Functions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requests {
    private Retrofit retrofit;
    private ApiService api;
    private final App app = App.getInstance();
    private OkHttpClient okHttpClient;

    public Requests() {
        initRetrofit();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        requestBuilder.addHeader("User-Agent", config.APP_NAME);
                        if (App.getToken() != null && !App.getToken().isEmpty())
                            requestBuilder.addHeader("Authorization", "Bearer " + App.getToken());
                        else Functions.logWriteError("TOKEN is empty!");
                        return chain.proceed(requestBuilder.build());
                    }
                })
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(ApiService.class);
    }

    public void cancellAllRequests(){
        okHttpClient.dispatcher().cancelAll();
    }

    public ApiService getApi() {
        return api;
    }

}
