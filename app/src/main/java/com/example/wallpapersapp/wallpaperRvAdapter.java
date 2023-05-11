package com.example.wallpapersapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class wallpaperRvAdapter extends RecyclerView.Adapter<wallpaperRvAdapter.ViewHolder> {
    private ArrayList<String> wallpaperRvArrayList;
    private Context context;

    public wallpaperRvAdapter(ArrayList<String> wallpaperRvArrayList, Context context) {
        this.wallpaperRvArrayList = wallpaperRvArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public wallpaperRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_rv_items,parent,false);
        return new wallpaperRvAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull wallpaperRvAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(wallpaperRvArrayList.get(position)).into(holder.wallpaperIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,wallpaper_activity.class);
                i.putExtra("imgUrl" , wallpaperRvArrayList.get(position));
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperRvArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private ImageView wallpaperIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wallpaperIv = itemView.findViewById(R.id.idIvWallpaper);

        }
    }
}
