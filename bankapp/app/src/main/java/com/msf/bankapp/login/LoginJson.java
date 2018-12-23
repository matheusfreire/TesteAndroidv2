package com.msf.bankapp.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.msf.bankapp.util.Error;

public class LoginJson {

    @SerializedName("userAccount")
    @Expose
    public UserAccount userAccount;
    @SerializedName("error")
    @Expose
    public Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}