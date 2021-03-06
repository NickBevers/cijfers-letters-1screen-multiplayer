package com.example.test_grid_cards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_player1_Number extends Fragment {

    public GridLayout cardGridLayout;
    View v;
    Gamestate_viewmodel gameViewModel;
    Number_viewmodel numberViewModel;
    Timer t = new Timer();
    private static final int PERIOD = 1000;
    public MutableLiveData<Integer> number = new MutableLiveData<Integer>();
    MutableLiveData<Integer> ronde;

    public Fragment_player1_Number() {
        // Required empty public constructor
        super(R.layout.number);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.number, container, false);
        number.setValue(0);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardGridLayout = v.findViewById(R.id.gridlayout);
        gameViewModel = new ViewModelProvider(requireActivity()).get(Gamestate_viewmodel.class);
        numberViewModel = new ViewModelProvider(requireActivity()).get(Number_viewmodel.class);


        v.findViewById(R.id.btn_low_number).setOnClickListener(view -> {
            numberViewModel.pickLowNumber();
            //Log.d("TAG", "LOW");
        });

        v.findViewById(R.id.btn_high_number).setOnClickListener(view -> {
            //Log.d("TAG", "HIGH");
            numberViewModel.pickHighNumber();
        });



        numberViewModel.getNumbers().observe(getViewLifecycleOwner(), numberArray -> {
            //Log.d("TAG", "onActivityCreated: " + numberArray);
            if (numberArray.size() > 0 && numberArray.size() <= 6){
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(String.valueOf(numberArray.get(numberArray.size()-1)));
                cardGridLayout.addView(cardView);
            }

            if (numberArray.size() == 6){
                TextView tv = v.findViewById(R.id.tv_random);
                /*int randomNum = numberViewModel.pickRandom().getValue();*/
                int randomNum = numberViewModel.randomNum;
                tv.setText(String.valueOf(randomNum));
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
                }
                else {
                    ronde = gameViewModel.getRound();
                    //Log.d("TAG", "Timer: TIMEEEEEE " + ronde.getValue());

                    if (ronde.getValue().equals(0)){
                        ((MainActivity) requireActivity()).setRound(1);
                        //Log.d("TAG", "IF" + ronde.getValue());
                    }
                    else{
                        ((MainActivity) requireActivity()).setRound(0);
                        //Log.d("TAG", "ELSE" + ronde.getValue());
                    }
                    cancel();
                }

            }
        }, 1000, PERIOD);
    }

    @Override
    public void onDestroyView() {
        Number_viewmodel numberViewModel = new ViewModelProvider(requireActivity()).get(Number_viewmodel.class);
        super.onDestroyView();
        numberViewModel.clearNumber();
    }

}