package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class NumberFrag extends Fragment {
    Number_viewmodel viewModel;

    public NumberFrag() {
        // Required empty public constructor
        super(R.layout.number_frag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(Number_viewmodel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.number_frag, container, false);
        v.findViewById(R.id.btn_low_number).setOnClickListener(view -> viewModel.pickLowNumber());
        v.findViewById(R.id.btn_high_number).setOnClickListener(view -> viewModel.pickHighNumber());

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.clearNumber();
    }
}