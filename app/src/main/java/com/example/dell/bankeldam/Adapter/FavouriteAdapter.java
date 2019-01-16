package com.example.dell.bankeldam.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.bankeldam.Model.Favourite_Data;
import com.example.dell.bankeldam.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter  extends RecyclerView.Adapter<FavouriteAdapter.Filesviewholder> {
    private Context context;
    private List<Favourite_Data> data;
    private LayoutInflater layoutInflater;
    public FavouriteAdapter(Context context, List<Favourite_Data> data) {
        this.context = context;
        this.data = data;
        layoutInflater = layoutInflater.from(context);
    }
    @NonNull
    @Override
    public FavouriteAdapter.Filesviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        view = layoutInflater.inflate(R.layout.favourite_list_items, null);
        FavouriteAdapter.Filesviewholder filesviewholder = new FavouriteAdapter.Filesviewholder(view);
        return filesviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Filesviewholder filesviewholder, int i) {
        Favourite_Data currentposition = data.get(i);
        filesviewholder.savechild_txt.setText(currentposition.getTitle());
        Picasso.get().load(currentposition.getThumbnailFullPath())
//                .resize(200,200)
                .into(filesviewholder.kids_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class Filesviewholder extends RecyclerView.ViewHolder {
        // Button favourite_btn;
        TextView savechild_txt;
        ImageView kids_img;
        public Filesviewholder(@NonNull View itemView) {
            super(itemView);
            //  favourite_btn = itemView.findViewById(R.id.btn_favourite);
            savechild_txt = itemView.findViewById(R.id.txt_savechildren);
            kids_img = itemView.findViewById(R.id.img_kids);
        }
    }
}
