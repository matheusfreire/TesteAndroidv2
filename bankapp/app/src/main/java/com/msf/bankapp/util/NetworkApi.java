package com.msf.bankapp.util;

import com.msf.bankapp.Login.LoginJson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkApi {

    @FormUrlEncoded
    @POST("login")
    Call<LoginJson> callLogin(@Field("user") String username,@Field("password") String password);
}
