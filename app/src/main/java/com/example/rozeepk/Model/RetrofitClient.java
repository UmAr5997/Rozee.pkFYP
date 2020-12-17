package com.example.rozeepk.Model;

import com.example.rozeepk.Api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String base_url="http://192.168.0.107:4700/api/demo/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance()
    {
        if(mInstance==null)
        {
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }
    public Api getApi()
    {
        return retrofit.create(Api.class);
    }
}
