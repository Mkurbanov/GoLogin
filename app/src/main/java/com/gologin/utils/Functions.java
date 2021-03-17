package com.gologin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.gologin.App;
import com.gologin.R;
import com.gologin.config;

import java.net.InetAddress;

public class Functions {
    public static void saveText(Context context, String key, String value) {
        SharedPreferences sPref = context.getSharedPreferences(config.PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sPref = context.getSharedPreferences(config.PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(key, value);
        ed.apply();
    }

    public static String loadText(Context context, String key) {
        SharedPreferences sPref = context.getSharedPreferences(config.PREFERENCES, context.MODE_PRIVATE);
        return sPref.getString(key, "");
    }

    public static int loadInt(Context context, String key) {
        SharedPreferences sPref = context.getSharedPreferences(config.PREFERENCES, context.MODE_PRIVATE);
        return sPref.getInt(key, 0);
    }

    public static void logWrite(String message) {
        Log.i(config.LOG_TAG, message);
    }

    public static void logWriteError(String error_message) {
        Log.e(config.LOG_TAG, error_message);
    }

    public static boolean isEmailValid(String email) {
        return (email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getNetworkError(){
        if (!isNetworkConnected())
            return App.getInstance().getString(R.string.please_connect_to_the_network);
        else if (!isInternetAvailable())
            return App.getInstance().getString(R.string.no_connection);
        else return App.getInstance().getString(R.string.error);
    }

    private static boolean isNetworkConnected() {
        Context context = App.getInstance().getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
