package com.example.sith3.getretrofitdata.Retrofit;

import com.example.sith3.getretrofitdata.Pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 2/3/2018.
 */

public interface GetCallData {

    @GET("/search/users")
    public Call<User> getDataFromCall(@Query("q") String q, @Query("page") int page);
}
