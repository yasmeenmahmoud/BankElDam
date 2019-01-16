package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.bankeldam.Adapter.PostsAdapter;
import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.Posts_Data;
import com.example.dell.bankeldam.Model.Posts_responsess;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Posts extends Fragment {
    PostsAdapter postsAdapter;
    List<Posts_Data> posts = new ArrayList<>();
    RecyclerView postrecyclerview;
    Button favourite_btn;
    String api_token;
    @BindView(R.id.simpleprogressBar1)
    ProgressBar simpleprogressBar1;
    Unbinder unbinder;

    public Posts() {
        // Required empty public constructor
    }

    View myview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_posts, container, false);
        addfileData();
        api_token = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, myview);
        simpleprogressBar1.setVisibility(View.VISIBLE);
        return myview;
    }

    private void addfileData() {
        postrecyclerview = myview.findViewById(R.id.myrecyclerview);
        postrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Posts_responsess> call = apiinterface.getPosts(api_token);
        call.enqueue(new Callback<Posts_responsess>() {
            @Override
            public void onResponse(Call<Posts_responsess> call, Response<Posts_responsess> response) {
                Posts_responsess postes_response = response.body();
                if (postes_response != null) {
                    posts = postes_response.getData().getData();
                    postsAdapter = new PostsAdapter(getContext(), posts);
                    postrecyclerview.setAdapter(postsAdapter);
                    simpleprogressBar1.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<Posts_responsess> call, Throwable t) {
                Toast.makeText(getContext(), "failed added ", Toast.LENGTH_SHORT).show();
                simpleprogressBar1.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}



