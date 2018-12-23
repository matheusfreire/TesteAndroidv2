package com.msf.bankapp.statement;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementJson {

    @SerializedName("statementList")
    @Expose
    public List<Statement> statementList = null;
    @SerializedName("error")
    @Expose
    public Error error;

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}