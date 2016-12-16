package com.example.easyretrofit;

import android.app.Application;

import com.example.easyretrofit.di.component.DaggerNetworkComponent;
import com.example.easyretrofit.di.component.NetworkComponent;


public class MyApplication extends Application {

    private static NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.create();
    }

    public static NetworkComponent getNetworkComponent(){
        return networkComponent;
    }
}
