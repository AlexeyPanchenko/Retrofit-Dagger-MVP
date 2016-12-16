package com.example.easyretrofit.Api;

import com.example.easyretrofit.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

    @GET("api/get")
    Call<List<PostModel>> getData(
            @Query("name") String resourceName,
            @Query("num") int count);
}
