package com.example.campingtoolsandequipment.ui.allcategories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campingtoolsandequipment.Adapter.NavCategoriesAdapter;
import com.example.campingtoolsandequipment.Adapter.PopularAdapter;
import com.example.campingtoolsandequipment.Models.NavCategoriesModel;
import com.example.campingtoolsandequipment.Models.popular_model;
import com.example.campingtoolsandequipment.R;
import com.example.campingtoolsandequipment.databinding.AllCategoriesFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesFragment extends Fragment {

    FirebaseFirestore db;
    private AllCategoriesFragmentBinding binding;
    RecyclerView recyclerView;
    List<NavCategoriesModel> navCategoriesModelList;
    NavCategoriesAdapter navCategoriesAdapter;
    ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllCategoriesViewModel allCategoriesViewModel =
                new ViewModelProvider(this).get(AllCategoriesViewModel.class);

        binding = AllCategoriesFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.cat_rec);
        db = FirebaseFirestore.getInstance();
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        //Popular Item
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        navCategoriesModelList= new ArrayList<>();
        navCategoriesAdapter = new NavCategoriesAdapter(getActivity(),navCategoriesModelList);
        recyclerView.setAdapter(navCategoriesAdapter);

        db.collection("NavCategories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NavCategoriesModel navCategoriesModel = document.toObject(NavCategoriesModel.class);
                                navCategoriesModelList.add(navCategoriesModel);
                                navCategoriesAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });
        return root;
    }

}