package com.example.campingtoolsandequipment.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campingtoolsandequipment.Models.CardModel;
import com.example.campingtoolsandequipment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    List<CardModel> cardModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;

    public CardAdapter(Context context, List<CardModel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(cardModelList.get(position).getProductName());
        holder.price.setText(cardModelList.get(position).getProductPrice());
        holder.date.setText(cardModelList.get(position).getCurrentDate());
        holder.time.setText(cardModelList.get(position).getCurrentTime());
        holder.quantity.setText(cardModelList.get(position).getTotalQuantity());
        holder.total_price.setText(String.valueOf(cardModelList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("CurrentUser").document(mAuth.getCurrentUser().getUid()).
                        collection("AddToCart")
                        .document(cardModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    cardModelList.remove(cardModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context,"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,date,time,quantity,total_price;
        ImageView deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            total_price = itemView.findViewById(R.id.total_price);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }
}
