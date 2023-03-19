package com.project.healthcare;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    List<ItemData> itemlist;
    Context context;

    FragmentActivity activity;

    public ServicesAdapter(List<ItemData> itemlist, Context context, FragmentActivity activity) {
        this.itemlist = itemlist;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {

        holder.title.setText(itemlist.get(position).getTitle());
        Glide.with(holder.imageView.getContext()).load(itemlist.get(position).getImageUrl()).into(holder.imageView);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("imageurl", itemlist.get(position).getImageUrl());
                bundle.putString("title", itemlist.get(position).getTitle());
                bundle.putString("description", itemlist.get(position).getDescription());

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);

                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainContainer, detailFragment);
                ft.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        CardView mainlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mimageview);
            title = itemView.findViewById(R.id.mtitle);
            mainlayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
