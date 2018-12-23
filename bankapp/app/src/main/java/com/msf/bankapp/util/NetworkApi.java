package com.msf.bankapp.util;

import com.msf.bankapp.login.LoginJson;
import com.msf.bankapp.statement.Statement;
import com.msf.bankapp.statement.StatementJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkApi {

    @FormUrlEncoded
    @POST("login")
    Call<LoginJson> callLogin(@Field("user") String username,@Field("password") String password);

    @GET("statements/{id}")
    Call<StatementJson> callStatement(@Path("id") int idMovie);
}
