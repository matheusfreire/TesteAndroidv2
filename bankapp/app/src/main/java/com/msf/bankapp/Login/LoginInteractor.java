package com.msf.bankapp.Login;

import android.util.Log;

import com.msf.bankapp.util.NetworkApi;
import com.msf.bankapp.util.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginInteractor {

    private static final String TAG = LoginInteractor.class.getSimpleName();

    private LoginListener mListener;

    LoginInteractor(LoginListener listener){
        this.mListener = listener;
    }


    void attemptToLogin(String user, String password){
        RetrofitClientInstance.getRetrofitInstance().create(NetworkApi.class)
                .callLogin(user, password).enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                if(response.isSuccessful()){
                    mListener.onDataRetrieved(response.body().getUserAccount());
                }
            }

            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {
                Log.d(TAG, "ERROR");
            }
        });
    }


    interface LoginListener{
        void onDataRetrieved(UserAccount userAccount);
    }
}
