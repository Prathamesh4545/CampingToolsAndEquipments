package com.example.campingtoolsandequipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.campingtoolsandequipment.Models.CardModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<CardModel> list = (ArrayList<CardModel>) getIntent().getSerializableExtra("itemList");

        if(list!=null && list.size() > 0){
            for (CardModel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());

                firestore.collection("CurrentUser").document(mAuth.getCurrentUser().getUid()).
                        collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlaceOrderActivity.this, "Your Order Has Been Placed!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}