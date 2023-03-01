package com.example.campingtoolsandequipment.Adapter;

import android.annotation.SuppressLint;
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
import com.example.campingtoolsandequipment.Models.NavCategoriesModel;
import com.example.campingtoolsandequipment.NavCategoryActivity;
import com.example.campingtoolsandequipment.R;

import java.util.List;

public class NavCategoriesAdapter extends RecyclerView.Adapter<NavCategoriesAdapter.ViewHolder> {

    private Context context;
    private List<NavCategoriesModel> navCategoriesModelList;

    public NavCategoriesAdapter(Context context, List<NavCategoriesModel> navCategoriesModelList) {
        this.context = context;
        this.navCategoriesModelList = navCategoriesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(navCategoriesModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navCategoriesModelList.get(position).getName());
        holder.description.setText(navCategoriesModelList.get(position).getDescription());
        holder.discount.setText(navCategoriesModelList.get(position).getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type",navCategoriesModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return navCategoriesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.cat_nav_name);
            description = itemView.findViewById(R.id.cat_nav_desc);
            discount = itemView.findViewById(R.id.cat_nav_discount);
        }
    }
}

