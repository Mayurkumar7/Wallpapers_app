package com.example.wallpapersapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class categoryRvAdapter extends RecyclerView.Adapter<categoryRvAdapter.ViewHolder> {
    private ArrayList<categoryRvModel> categoryRvModelArrayList;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public categoryRvAdapter(ArrayList<categoryRvModel> categoryRvModelArrayList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRvModelArrayList = categoryRvModelArrayList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public categoryRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_rv_items,parent,false);
        return new categoryRvAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull categoryRvAdapter.ViewHolder holder, int position) {
        categoryRvModel categoryRvModel = categoryRvModelArrayList .get(position);
        holder.categoryTv.setText(categoryRvModel.getCategory());
        Glide.with(context).load(categoryRvModel.getCategoryIvURL()).into(holder.categoryIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);


            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryRvModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryTv;
        private ImageView categoryIv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTv = itemView.findViewById(R.id.idTvCategory);
            categoryIv = itemView.findViewById(R.id.idIvCategory);

        }
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);

    }
}

