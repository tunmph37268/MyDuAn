package com.example.asm_andapi103_ph37268.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCart {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private CartData data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public CartData getData() {
        return data;
    }
}

