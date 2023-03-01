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
import com.example.campingtoolsandequipment.Models.popular_model;
import com.example.campingtoolsandequipment.R;
import com.example.campingtoolsandequipment.ViewAllActivity;
import com.example.campingtoolsandequipment.databinding.PopularItemBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<popular_model>popular_modelList;

    public PopularAdapter(Context context, List<popular_model> popular_modelList) {
        this.context = context;
        this.popular_modelList = popular_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popular_modelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popular_modelList.get(position).getName());
        holder.rating.setText(popular_modelList.get(position).getRating());
        holder.description.setText(popular_modelList.get(position).getDescription());
        holder.discount.setText(popular_modelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popular_modelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popular_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_desc);
            rating = itemView.findViewById(R.id.pop_rating);
            discount = itemView.findViewById(R.id.pop_discount);
        }
    }
}
