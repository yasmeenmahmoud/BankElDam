package com.example.dell.bankeldam.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.bankeldam.Fragments.PostsDetails_Fragment;
import com.example.dell.bankeldam.Model.Posts_Data;
import com.example.dell.bankeldam.R;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.Filesviewholder> {
    private Context context;
    private List<Posts_Data> data;
    private LayoutInflater layoutInflater;

    public PostsAdapter(Context context, List<Posts_Data> data) {
        this.context = context;
        this.data = data;
        layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public Filesviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        view = layoutInflater.inflate(R.layout.file_list_items, null);
        Filesviewholder filesviewholder = new Filesviewholder(view);
        return filesviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Filesviewholder filesviewholder, int i) {

        final Posts_Data currentposition = data.get(i);
        filesviewholder.savechild_txt.setText(currentposition.getTitle());
        Picasso.get().load(currentposition.getThumbnailFullPath())
//                .resize(200,200)
                .into(filesviewholder.kids_img);
filesviewholder.favourite_btn.setFavorite(currentposition.getIsFavourite());
//        if (currentposition.getIsFavourite().equals(true))
//            filesviewholder.favourite_btn.setBackgroundResource(R.drawable.loveit);
//        else {
//            filesviewholder.favourite_btn.setBackgroundResource(R.drawable.favoriteicon);
//        }
//        filesviewholder.favourite_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentposition.getIsFavourite().equals(true))
//                    //filesviewholder.favourite_btn.setBackgroundResource(R.drawable.favoriteicon);
//                    currentposition.setIsFavourite(false);
//                 {
//                    currentposition.setIsFavourite(true);
//                }
//            }
//        });
        filesviewholder.favourite_btn.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        //
                    }
                });

        filesviewholder.postlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                PostsDetails_Fragment postsDetails_fragment = new PostsDetails_Fragment();
                postsDetails_fragment.posts_data = currentposition;
                transaction.replace(R.id.flContent, postsDetails_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Filesviewholder extends RecyclerView.ViewHolder {
        TextView savechild_txt;
        ImageView kids_img;
        RelativeLayout postlayout;
        MaterialFavoriteButton favourite_btn;

        public Filesviewholder(@NonNull View itemView) {
            super(itemView);
            favourite_btn = itemView.findViewById(R.id.btn_favourite);
            savechild_txt = itemView.findViewById(R.id.txt_savechildren);
            kids_img = itemView.findViewById(R.id.img_kids);
            postlayout = itemView.findViewById(R.id.postslayout1);

        }
    }
}
