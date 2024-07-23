package com.example.asm_andapi103_ph37268.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    private Service requesInterface;
    public  HttpRequest(){
        requesInterface=new Retrofit.Builder().baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service.class);
    }
    public Service callApi(){
        return requesInterface;
    }
}
