package com.gologin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.gologin.R;
import com.gologin.config;
import com.gologin.ui.login.LoginActivity;
import com.gologin.utils.Functions;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Functions.loadText(SplashActivity.this, config.app.TOKEN).isEmpty()) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(SplashActivity.this, (ImageView) findViewById(R.id.imageView_logo), "logo");
                    startActivity(intent, options.toBundle());
                } else startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}