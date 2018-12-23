package com.msf.bankapp.statement;

import android.util.Log;

import com.msf.bankapp.util.NetworkApi;
import com.msf.bankapp.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class StatementInteractor {

    private static final String TAG = StatementInteractor.class.getSimpleName();

    private StatementListener mStatementListener;

    public StatementInteractor(StatementListener mStatementListener) {
        this.mStatementListener = mStatementListener;
    }

    void getStatement(int userAccountId){
        RetrofitClientInstance.getRetrofitInstance().create(NetworkApi.class)
                .callStatement(userAccountId).enqueue(new Callback<StatementJson>() {
            @Override
            public void onResponse(Call<StatementJson> call, Response<StatementJson> response) {
                if(response.isSuccessful()){
                    mStatementListener.onStatementRetrieved(response.body().getStatementList(), false);
                } else {
                    mStatementListener.onStatementRetrieved(null, false);
                }
            }

            @Override
            public void onFailure(Call<StatementJson> call, Throwable t) {
                mStatementListener.onStatementRetrieved(null, true);
                Log.d(TAG, "ERROR");
            }
        });
    }

    interface StatementListener{
        void onStatementRetrieved(List<Statement> listStatement, boolean hasError);
    }
}
