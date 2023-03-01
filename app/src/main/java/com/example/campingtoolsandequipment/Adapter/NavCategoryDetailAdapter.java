package com.example.campingtoolsandequipment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campingtoolsandequipment.Models.NavCategoryDetailedModel;
import com.example.campingtoolsandequipment.NavCategoryActivity;
import com.example.campingtoolsandequipment.R;

import java.util.List;

public class NavCategoryDetailAdapter extends RecyclerView.Adapter<NavCategoryDetailAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailedModel> list;

    public NavCategoryDetailAdapter(Context context, List<NavCategoryDetailedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_del_img);
            name = itemView.findViewById(R.id.cat_nav_del_name);
            price = itemView.findViewById(R.id.del_price);
        }
    }
}
