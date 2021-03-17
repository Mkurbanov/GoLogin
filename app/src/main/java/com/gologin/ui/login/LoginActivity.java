package com.gologin.ui.login;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gologin.R;
import com.gologin.config;
import com.gologin.ui.MainActivity;
import com.gologin.ui.reg.RegActivity;
import com.gologin.utils.Functions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((ViewGroup) findViewById(R.id.rl)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        TextInputLayout textInputEmail = findViewById(R.id.text_input_email);
        TextInputLayout textInputPassword = findViewById(R.id.text_input_password);
        TextInputEditText textInputEditEmail = findViewById(R.id.edit_email);
        TextInputEditText textInputEditPassword = findViewById(R.id.edit_password);
        TextView tvSignUp = findViewById(R.id.textView_sign_up);
        MaterialButton btnLogin = findViewById(R.id.button_login);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString());
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString());
            }
        };

        textInputEditEmail.addTextChangedListener(afterTextChangedListener);
        textInputEditPassword.addTextChangedListener(afterTextChangedListener);
        textInputEditPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString());
                }
                return false;
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }

                if (loginFormState.getEmailError() != null) {
                    textInputPassword.setVisibility(View.GONE);
                    addLayoutParam(btnLogin, textInputEmail.getId());
                    textInputEmail.setError(getString(loginFormState.getEmailError()));
                } else {
                    textInputPassword.setVisibility(View.VISIBLE);
                    addLayoutParam(btnLogin, textInputPassword.getId());
                    textInputEmail.setError(null);
                }

                if (loginFormState.getPasswordError() != null) {
                    textInputPassword.setError(getString(loginFormState.getPasswordError()));
                } else textInputPassword.setError(null);

                btnLogin.setEnabled(loginFormState.getEmailError() == null && loginFormState.getPasswordError() == null);
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                if (loginResult == null)
                    return;

                if (loginResult.getErrorText() != null){
                    Functions.toast(LoginActivity.this, loginResult.getErrorText());
                    return;
                }

                if (loginResult.getErrorResponse() != null){
                    Functions.toast(LoginActivity.this, loginResult.getErrorResponse().getMessage());
                    return;
                }

                if (loginResult.getLoginResponse() != null){
                    Functions.saveText(LoginActivity.this, config.app.TOKEN, loginResult.getLoginResponse().getToken());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void addLayoutParam(View view, int toBottomViewId) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams());
        layoutParams.addRule(RelativeLayout.BELOW, toBottomViewId);
        view.setLayoutParams(layoutParams);
    }
}