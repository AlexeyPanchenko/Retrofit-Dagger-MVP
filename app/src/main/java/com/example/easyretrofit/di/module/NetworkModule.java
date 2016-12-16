package com.example.easyretrofit.di.module;


import com.example.easyretrofit.Api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private Retrofit retrofit;
    private Api myApi;

    @Provides
    @Singleton
    public Api provideApi(){

        retrofit =  new Retrofit.Builder()
                .baseUrl("http://www.umori.li/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(Api.class);

        return myApi;
    }
}
