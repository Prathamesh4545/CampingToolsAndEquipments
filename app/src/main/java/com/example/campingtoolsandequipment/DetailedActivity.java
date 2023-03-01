package com.example.campingtoolsandequipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.campingtoolsandequipment.Models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailed_img;
    TextView price,rating,description,quantity;
    ImageView add_item,remove_item;
    Button add_to_card;
    int totalQuantity = 1;
    int totalPrice = 0;
    ViewAllModel viewAllModel = null;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        getSupportActionBar().setTitle("Detailed Product");

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        detailed_img = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_description);
        add_item = findViewById(R.id.add_item);
        remove_item = findViewById(R.id.remove_item);
        add_to_card = findViewById(R.id.add_to_card);
        quantity = findViewById(R.id.quantity);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        totalPrice = viewAllModel.getPrice() * totalQuantity;

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailed_img);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price:₹"+viewAllModel.getPrice());
        }
        add_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCard();
            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 10){
                    totalQuantity ++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }
            }
        });
        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 0){
                    totalQuantity --;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

    }

    private void addedToCard() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(mAuth.getCurrentUser().getUid()).
                collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Item Is Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}