package com.example.campingtoolsandequipment.ui.myWishList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.campingtoolsandequipment.databinding.MyWishListFragmentBinding;


public class MyWishListFragment extends Fragment {

    private MyWishListFragmentBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyWishListViewModel myWishListViewModel =
                new ViewModelProvider(this).get(MyWishListViewModel.class);

        binding = MyWishListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

}