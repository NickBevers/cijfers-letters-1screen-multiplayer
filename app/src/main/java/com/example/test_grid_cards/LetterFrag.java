package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LetterFrag extends Fragment {
    Letter_viewmodel viewModel;

    public LetterFrag() {
        // Required empty public constructor
        super(R.layout.letter_frag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(Letter_viewmodel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.letter_frag, container, false);
        v.findViewById(R.id.btn_vowel).setOnClickListener(view -> viewModel.pickVowel());
        v.findViewById(R.id.btn_consonant).setOnClickListener(view -> viewModel.pickConsonant());

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.clearLetter();
    }
}