package com.msf.bankapp.statement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("value")
    @Expose
    public Double value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}