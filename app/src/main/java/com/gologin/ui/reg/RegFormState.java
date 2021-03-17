package com.gologin.ui.reg;

import androidx.annotation.Nullable;

public class RegFormState {
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer passwordConfirmError;
    private boolean isDataValid;

    RegFormState(@Nullable Integer emailError, @Nullable Integer passwordError, @Nullable Integer passwordConfirmError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.passwordConfirmError = passwordConfirmError;
        this.isDataValid = false;
    }

    RegFormState(boolean isDataValid) {
        this.emailError = null;
        this.passwordError = null;
        this.passwordConfirmError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getPasswordConfirmError() {
        return passwordConfirmError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}