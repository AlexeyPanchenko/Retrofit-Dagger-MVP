package com.example.easyretrofit.di.component;


import com.example.easyretrofit.UI.MainActivity;
import com.example.easyretrofit.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(MainActivity mainActivity);
}
