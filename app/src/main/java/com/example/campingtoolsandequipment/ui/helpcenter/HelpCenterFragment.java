package com.example.campingtoolsandequipment.ui.helpcenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.campingtoolsandequipment.databinding.HelpCenterFragmentBinding;


public class HelpCenterFragment extends Fragment {


    private HelpCenterFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HelpCenterViewModel helpCenterViewModel =
                new ViewModelProvider(this).get(HelpCenterViewModel.class);

        binding = HelpCenterFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        helpCenterViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

}