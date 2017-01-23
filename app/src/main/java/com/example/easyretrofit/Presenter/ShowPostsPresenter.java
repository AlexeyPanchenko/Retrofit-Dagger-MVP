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
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class ShowPostsPresenter extends MvpBasePresenter<ShowPostsView>{

    private List<PostModel> posts = new ArrayList<>();
    private static Subscription sudscription;

    @Inject
    Api api;

    public ShowPostsPresenter(List<PostModel> posts){
        this.posts = posts;
    }

    public void doUnsubscribe(){
        if(sudscription != null && !sudscription.isUnsubscribed()){
            sudscription.unsubscribe();
            Log.d("TTT", "doUnsubscribe +++ ");
        }

    }

    public void getPosts(final List<PostModel> posts, final Context context){

        MyApplication.getNetworkComponent().inject(this);

        sudscription = api.getData("bash", 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PostModel>>() {
                    @Override
                    public void call(List<PostModel> postModels) {
                        posts.addAll(postModels);
                        getView().showPosts();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(context, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
