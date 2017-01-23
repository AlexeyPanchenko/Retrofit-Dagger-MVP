package com.example.easyretrofit.Api;

import com.example.easyretrofit.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface Api {

    @GET("api/get")
    Observable<List<PostModel>> getData(
            @Query("name") String resourceName,
            @Query("num") int count);
}
