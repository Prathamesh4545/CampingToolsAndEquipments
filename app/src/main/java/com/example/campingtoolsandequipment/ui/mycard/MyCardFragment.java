package com.example.campingtoolsandequipment.ui.mycard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campingtoolsandequipment.Adapter.CardAdapter;
import com.example.campingtoolsandequipment.Models.CardModel;
import com.example.campingtoolsandequipment.PlaceOrderActivity;
import com.example.campingtoolsandequipment.R;
import com.example.campingtoolsandequipment.databinding.MyCardFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCardFragment extends Fragment {

    private MyCardFragmentBinding binding;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    List<CardModel> cardModelList;
    TextView overTotalAmount;
    ProgressBar progressBar;
    Button buyNow;

    public MyCardFragment() {
            //Required
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyCardViewModel myCardViewModel =
                new ViewModelProvider(this).get(MyCardViewModel.class);

        binding = MyCardFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        buyNow = root.findViewById(R.id.buy_now);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.overTotalAmount);

        cardModelList = new ArrayList<>();
        cardAdapter = new CardAdapter(getActivity(),cardModelList);
        recyclerView.setAdapter(cardAdapter);

        db.collection("CurrentUser")
                .document(mAuth.getCurrentUser().getUid())
                .collection("AddToCart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        String documentId = document.getId();
                        CardModel cardModel = document.toObject(CardModel.class);
                        cardModel.setDocumentId(documentId);
                        cardModelList.add(cardModel);
                        cardAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    calcualteTotalAction(cardModelList);
                }
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
                intent.putExtra("itemList", (Serializable) cardModelList);
                startActivity(intent);
            }
        });

        return root;
    }

    private void calcualteTotalAction(List<CardModel> cardModelList) {
        double totalAmount = 0.0;
        for (CardModel cardModel: cardModelList){
            totalAmount += cardModel.getTotalPrice();
        }
        overTotalAmount.setText("Total Model :"+ totalAmount);
    }
}