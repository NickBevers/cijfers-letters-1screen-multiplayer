package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

public class Fragment_player2 extends Fragment {

    public MutableLiveData<Integer> number = new MutableLiveData<Integer>();
    public GridLayout cardGridLayout;
    Letter_viewmodel LetterViewModel;
    Number_viewmodel NumberViewModel;

    public Fragment_player2() {
        // Required empty public constructor
        super(R.layout.activity_fragment_player1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_player2, container, false);
        number.setValue(0);
        cardGridLayout = v.findViewById(R.id.gridlayout);

        for (int i = 0; i < 6; i++) {
            View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
            TextView tv = cardView.findViewById(R.id.number_card_text);
            tv.setText(Integer.toString(i + 1));
            cardGridLayout.addView(cardView);
        }

        v.setRotation(180);
        v.requestLayout();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NumberViewModel = new ViewModelProvider(this).get(Number_viewmodel.class);
        LetterViewModel = new ViewModelProvider(this).get(Letter_viewmodel.class);
    }

}