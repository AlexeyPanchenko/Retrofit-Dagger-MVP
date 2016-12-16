package com.example.easyretrofit.Presenter;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.easyretrofit.Api.Api;
import com.example.easyretrofit.MyApplication;
import com.example.easyretrofit.View.ShowPostsView;
import com.example.easyretrofit.model.PostModel;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPostsPresenter extends MvpBasePresenter<ShowPostsView>{

    List<PostModel> posts = new ArrayList<>();

    @Inject
    Api api;

    public ShowPostsPresenter(List<PostModel> posts){
        this.posts = posts;
    }

    public void getPosts(final List<PostModel> posts, final Context context){

        MyApplication.getNetworkComponent().inject(this);


        api.getData("bash", 50).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                Log.d("TAG", "body = " + response.body());
                posts.addAll(response.body());
                getView().showPosts();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(context, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
