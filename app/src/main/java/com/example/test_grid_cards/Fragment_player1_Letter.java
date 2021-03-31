package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_player1_Letter extends Fragment {
    public GridLayout cardGridLayout;
    View v;
    Gamestate_viewmodel GameViewModel;
    Timer t = new Timer();
    private static final int PERIOD = 1000;
    public MutableLiveData<Integer> number = new MutableLiveData<Integer>();

    public Fragment_player1_Letter() {
        // Required empty public constructor
        super(R.layout.number);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.letter, container, false);
        number.setValue(0);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameViewModel = new ViewModelProvider(this).get(Gamestate_viewmodel.class);
        cardGridLayout = v.findViewById(R.id.gridlayout);
        Letter_viewmodel letterViewModel = new ViewModelProvider(requireActivity()).get(Letter_viewmodel.class);


        v.findViewById(R.id.btn_vowel).setOnClickListener(view -> {
            letterViewModel.pickVowel();
        });

        v.findViewById(R.id.btn_consonant).setOnClickListener(view -> {
            letterViewModel.pickConsonant();
        });



        letterViewModel.getLetters().observe(getViewLifecycleOwner(), letterArray -> {
            if (letterArray.size() > 0 && letterArray.size() <= 6){
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(String.valueOf(letterArray.get(letterArray.size()-1)));
                cardGridLayout.addView(cardView);
            }

            if (letterArray.size() == 6){
                startTimer(requireView());
            }
        });

        ProgressBar pb = requireActivity().findViewById(R.id.progress_bar);
        //pb::setProgress == (number -> pb.setProgress(number)
        number.observe(requireActivity() , pb::setProgress);
    }

    public void startTimer(View w) {
        long startTime = System.currentTimeMillis();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - startTime <= 5000) {
                    number.postValue(number.getValue() + 1);
                } else {
                    Log.d("TAG", "Timer: TIMEEEEEE ");
                    cancel();
                }

            }
        }, 0, PERIOD);
    }

}