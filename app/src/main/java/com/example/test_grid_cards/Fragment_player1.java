package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;

public class Fragment_player1 extends Fragment {

    private GridLayout cardGridLayout;
    public MutableLiveData<Integer> number = new MutableLiveData<Integer>();

    public Fragment_player1() {
        // Required empty public constructor
        super(R.layout.activity_fragment_player1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_player1, container, false);
        number.setValue(0);
        cardGridLayout = v.findViewById(R.id.gridlayout);

        for (int i = 0; i < 6; i++){
            View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
            TextView tv = cardView.findViewById(R.id.number_card_text);
            tv.setText(Integer.toString(i +1));
            cardGridLayout.addView(cardView);
        }


        return v;
    }
}