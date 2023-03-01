package com.example.campingtoolsandequipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.campingtoolsandequipment.Adapter.NavCategoryDetailAdapter;
import com.example.campingtoolsandequipment.Models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailAdapter adapter;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Detailed Product");
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.nav_cat_detailed_rec);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoryDetailAdapter(this,list);
        String type = getIntent().getStringExtra("type");
        recyclerView.setAdapter(adapter);


        //Getting gloves
        if (type != null && type.equalsIgnoreCase("gloves")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","gloves")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting carabiner
        if (type != null && type.equalsIgnoreCase("carabiner")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","carabiner")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                                list.add(navCategoryDetailedModel);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
        //Getting headware
        if (type != null && type.equalsIgnoreCase("headware")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","headware")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                                list.add(navCategoryDetailedModel);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
        //Getting ascender
        if (type != null && type.equalsIgnoreCase("ascender")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","ascender")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                                list.add(navCategoryDetailedModel);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
        //Getting bdevice
        if (type != null && type.equalsIgnoreCase("bdevice")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","bdevice")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                                list.add(navCategoryDetailedModel);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
    }
}