package com.gologin.ui.reg;

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
import com.gologin.utils.Functions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegActivity extends AppCompatActivity {
    RegViewModel regViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ((ViewGroup) findViewById(R.id.rl)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        regViewModel = new ViewModelProvider(this).get(RegViewModel.class);

        TextInputLayout textInputEmail = findViewById(R.id.text_input_email);
        TextInputLayout textInputPassword = findViewById(R.id.text_input_password);
        TextInputEditText textInputEditEmail = findViewById(R.id.edit_email);
        TextInputEditText textInputEditPassword = findViewById(R.id.edit_password);
        TextInputLayout textInputPasswordConfirm = findViewById(R.id.text_input_password_confirm);
        TextInputEditText textInputEditPasswordConfirm = findViewById(R.id.edit_password_confirm);
        TextView tvSignIn = findViewById(R.id.textView_sign_in);
        MaterialButton btnReg = findViewById(R.id.button_reg);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regViewModel.reg(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString(), textInputEditPasswordConfirm.getText().toString());
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
                regViewModel.regDataChanged(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString(), textInputEditPasswordConfirm.getText().toString());
            }
        };

        textInputEditEmail.addTextChangedListener(afterTextChangedListener);
        textInputEditPassword.addTextChangedListener(afterTextChangedListener);
        textInputEditPasswordConfirm.addTextChangedListener(afterTextChangedListener);
        textInputEditPasswordConfirm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    regViewModel.reg(textInputEditEmail.getText().toString(), textInputEditPassword.getText().toString(), textInputEditPasswordConfirm.getText().toString());
                }
                return false;
            }
        });

        regViewModel.getRegFormState().observe(this, new Observer<RegFormState>() {
            @Override
            public void onChanged(RegFormState regFormState) {
                if (regFormState == null) {
                    return;
                }

                if (regFormState.getEmailError() != null) {
                    textInputPassword.setVisibility(View.GONE);
                    textInputPasswordConfirm.setVisibility(View.GONE);
                    addLayoutParam(btnReg, textInputEmail.getId());
                    textInputEmail.setError(getString(regFormState.getEmailError()));
                } else {
                    textInputPassword.setVisibility(View.VISIBLE);
                    textInputPasswordConfirm.setVisibility(View.VISIBLE);
                    addLayoutParam(btnReg, textInputPasswordConfirm.getId());
                    textInputEmail.setError(null);
                }

                if (regFormState.getPasswordError() != null) {
                    textInputPassword.setError(getString(regFormState.getPasswordError()));
                } else textInputPassword.setError(null);

                if (regFormState.getPasswordConfirmError() != null) {
                    textInputPasswordConfirm.setError(getString(regFormState.getPasswordConfirmError()));
                } else textInputPasswordConfirm.setError(null);

                btnReg.setEnabled(regFormState.getEmailError() == null && regFormState.getPasswordError() == null && regFormState.getPasswordConfirmError() == null);
            }
        });

        regViewModel.getRegResult().observe(this, new Observer<RegResult>() {
            @Override
            public void onChanged(RegResult regResult) {
                if (regResult == null)
                    return;

                if (regResult.getErrorText() != null) {
                    Functions.toast(RegActivity.this, regResult.getErrorText());
                    return;
                }

                if (regResult.getErrorResponse() != null) {
                    Functions.toast(RegActivity.this, regResult.getErrorResponse().getMessage());
                    return;
                }

                if (regResult.getRegResponse() != null) {
                    Functions.saveText(RegActivity.this, config.app.TOKEN, regResult.getRegResponse().getToken());
                    Intent intent = new Intent(RegActivity.this, MainActivity.class);
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