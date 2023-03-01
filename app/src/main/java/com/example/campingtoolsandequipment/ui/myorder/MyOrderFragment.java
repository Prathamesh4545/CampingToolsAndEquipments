package com.example.campingtoolsandequipment.ui.myorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.campingtoolsandequipment.databinding.MyOrderFragmentBinding;


public class MyOrderFragment extends Fragment {

    private MyOrderViewModel myOrderViewModel;
    private MyOrderFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyOrderViewModel myOrderViewModel =
                new ViewModelProvider(this).get(MyOrderViewModel.class);

        binding = MyOrderFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

}