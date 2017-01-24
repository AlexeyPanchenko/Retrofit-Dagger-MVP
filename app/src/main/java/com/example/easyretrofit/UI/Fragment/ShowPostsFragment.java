package com.example.easyretrofit.UI.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.easyretrofit.Presenter.ShowPostsPresenter;
import com.example.easyretrofit.R;
import com.example.easyretrofit.UI.Adapter.PostsAdapter;
import com.example.easyretrofit.View.ShowPostsView;
import com.example.easyretrofit.model.PostModel;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowPostsFragment extends MvpFragment<ShowPostsView, ShowPostsPresenter> implements ShowPostsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.fragment)
    SwipeRefreshLayout layout;

    List<PostModel> posts;

    PostsAdapter postsAdapter;
    RecyclerView.ViewHolder holder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);
        posts = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        postsAdapter = new PostsAdapter(posts);
        recyclerView.setAdapter(postsAdapter);

        layout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);

        layout.setOnRefreshListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getPosts(posts, getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TTT", "Unsubscribe = " + presenter.getSudscription().isUnsubscribed());
    }

    @Override
    public void showPosts() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public ShowPostsPresenter createPresenter() {
        return new ShowPostsPresenter(posts);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.doUnsubscribe();
        Log.d("TTT", "Unsubscribe = " + presenter.getSudscription().isUnsubscribed());
    }

    @Override
    public void onRefresh() {
        layout.setRefreshing(true);
        postsAdapter = new PostsAdapter(posts);
        recyclerView.setAdapter(postsAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        presenter.getPosts(posts, getActivity());

        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setRefreshing(false);

            }
        }, 1200);

    }
}
