package br.com.borsoitech.pdv.model.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl("http://localhost:8080/").addConverterFactory(GsonConverterFactory.create()).build();
    }
}
