package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.Favourite_Data;
import com.example.dell.bankeldam.Model.Favourite_Response;
import com.example.dell.bankeldam.Adapter.FavouriteAdapter;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favourites_Fragment extends Fragment {
    FavouriteAdapter favouriteAdapter;
    List<Favourite_Data> favourites = new ArrayList<>();
    RecyclerView favouriterecyclerview;
    View view;

    public Favourites_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourites_, container, false);
        getFavouritesData();
        return view;
    }

    private void getFavouritesData() {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Favourite_Response> call = apiinterface.getFavourites();
        call.enqueue(new Callback<Favourite_Response>() {
            @Override
            public void onResponse(Call<Favourite_Response> call, Response<Favourite_Response> response) {
                Favourite_Response favouriteResponse = response.body();
                try {
                    Log.i("response", String.valueOf(response.body().getData().getData()));
                    favourites = favouriteResponse.getData().getData();
                    favouriterecyclerview = view.findViewById(R.id.myrecyclerview2);
                    favouriterecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    favouriteAdapter = new FavouriteAdapter(getContext(), favourites);
                    favouriterecyclerview.setAdapter(favouriteAdapter);
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<Favourite_Response> call, Throwable t) {
                Toast.makeText(getContext(), "failed added successeffully", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("المفضله");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
