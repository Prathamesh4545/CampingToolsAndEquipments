package com.example.campingtoolsandequipment.ui.trending;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.campingtoolsandequipment.databinding.TrendingFragmentBinding;


public class TrendingFragment extends Fragment {


    private TrendingFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendingViewModel trendingViewModel =
                new ViewModelProvider(this).get(TrendingViewModel.class);

        binding = TrendingFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

}