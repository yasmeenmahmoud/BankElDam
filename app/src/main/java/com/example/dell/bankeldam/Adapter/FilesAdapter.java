package com.example.dell.bankeldam.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.Filesviewholder> {
//    private Context context;
//    private ArrayList<FileClass> data;
//    private LayoutInflater layoutInflater;
//
//    public FilesAdapter(Context context, ArrayList<FileClass> data) {
//        this.context = context;
//        this.data = data;
//        layoutInflater = layoutInflater.from(context);
//
//    }
//
//    @NonNull
//    @Override
//    public Filesviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view;
//        view = layoutInflater.inflate(R.layout.file_list_items, null);
//        Filesviewholder filesviewholder = new Filesviewholder(view);
//        return filesviewholder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final Filesviewholder filesviewholder, int i) {
//
//        FileClass currentposition = data.get(i);
//        filesviewholder.savechild_txt.setText(currentposition.getSavechild());
//        filesviewholder.kids_img.setImageResource(currentposition.getKidsimage());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public class Filesviewholder extends RecyclerView.ViewHolder {
//        Button favourite_btn;
//        TextView savechild_txt;
//        ImageView kids_img;
//
//        public Filesviewholder(@NonNull View itemView) {
//            super(itemView);
//            favourite_btn = itemView.findViewById(R.id.btn_favourite);
//            savechild_txt = itemView.findViewById(R.id.txt_savechildren);
//            kids_img = itemView.findViewById(R.id.img_kids);
//
//            favourite_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    boolean isLoved = data.get(getAdapterPosition()).isLoved();
//                    if (isLoved == true) {
//                        favourite_btn.setBackgroundResource(R.drawable.loveit);
//                        data.get(getAdapterPosition()).setLoved(true);
//                    } else {
//                        favourite_btn.setBackgroundResource(R.drawable.favorits);
//                        data.get(getAdapterPosition()).setLoved(false);
//                    }
//                    notifyDataSetChanged();
////
//
//                }
//            });
//        }
//    }
//}
