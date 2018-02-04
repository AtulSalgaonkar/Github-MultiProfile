package com.example.sith3.getretrofitdata.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2/3/2018.
 */

public class RetrofitClient {

    private static final String BASE_URL = "https://api.github.com";
    private static Retrofit mInstance = null;

    private RetrofitClient() {

    }

    public static Retrofit getInstance() {
        if (mInstance == null)
            mInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return mInstance;
    }

}
