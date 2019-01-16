package com.example.dell.bankeldam.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.bankeldam.Model.Notification_Data;
import com.example.dell.bankeldam.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Filesviewholder> {
    private Context context;
    private List<Notification_Data> data;
    private LayoutInflater layoutInflater;

    public NotificationAdapter(Context context, List<Notification_Data> data) {
        this.context = context;
        this.data = data;
        layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public NotificationAdapter.Filesviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        view = layoutInflater.inflate(R.layout.notification_listitemview, null);
        NotificationAdapter.Filesviewholder filesviewholder = new NotificationAdapter.Filesviewholder(view);
        return filesviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.Filesviewholder filesviewholder, int i) {

        final Notification_Data currentposition = data.get(i);
        filesviewholder.notyfycontent.setText(currentposition.getContent());
        filesviewholder.notyfytitle.setText(currentposition.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Filesviewholder extends RecyclerView.ViewHolder {
        TextView notyfycontent;
        TextView notyfytitle;

        public Filesviewholder(@NonNull View itemView) {
            super(itemView);
            notyfycontent = itemView.findViewById(R.id.notifydate);
            notyfytitle = itemView.findViewById(R.id.notyifytitle);


        }
    }
}
