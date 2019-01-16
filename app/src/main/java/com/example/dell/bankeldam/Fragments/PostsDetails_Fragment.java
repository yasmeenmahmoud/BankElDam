package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.PostDetails_Response;
import com.example.dell.bankeldam.Model.Posts_Data;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsDetails_Fragment extends Fragment {
    SharedPereferenceClass sharedPereferenceClass = new SharedPereferenceClass();
    public Posts_Data posts_data = new Posts_Data();
    View view;
    @BindView(R.id.details_img)
    ImageView detailsImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_details)
    TextView txtDetails;
    Unbinder unbinder;
    String api_token;
    int post_id;

    public PostsDetails_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_posts_details_, container, false);
        unbinder = ButterKnife.bind(this, view);
        api_token = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
        post_id = posts_data.getId();
        getPostsDetails();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle(posts_data.getTitle());

    }

    private void getPostsDetails() {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<PostDetails_Response> call = apiinterface.getpostsDetails(api_token, post_id);
        call.enqueue(new Callback<PostDetails_Response>() {
            @Override
            public void onResponse(Call<PostDetails_Response> call, Response<PostDetails_Response> response) {
                PostDetails_Response postDetailsResponse = response.body();
                Log.i("MyResponse", response.body().getData().getTitle());
                txtTitle.setText(postDetailsResponse.getData().getTitle());
                txtDetails.setText(postDetailsResponse.getData().getContent());
                Picasso.get().load(postDetailsResponse.getData().getThumbnailFullPath()).into(detailsImg);
            }

            @Override
            public void onFailure(Call<PostDetails_Response> call, Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
