package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

public class Fragment_player1 extends Fragment {

    public MutableLiveData<Integer> number = new MutableLiveData<Integer>();
    public GridLayout cardGridLayout;
    Letter_viewmodel LetterViewModel;
    Number_viewmodel NumberViewModel;
    public ArrayList<View> cardList = new ArrayList<>();
    private ArrayList<Character> letterArray;
    View v;

    public Fragment_player1() {
        // Required empty public constructor
        super(R.layout.activity_fragment_player1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_fragment_player1, container, false);
        number.setValue(0);
        cardGridLayout = v.findViewById(R.id.gridlayout);


//        for (int i = 0; i < 6; i++){
//            View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
//            TextView tv = cardView.findViewById(R.id.number_card_text);
//            tv.setText(Integer.toString(i +1));
//            cardList.add(cardView);
//            Log.d("Cardarray", "arr: " + Arrays.toString(new ArrayList[]{cardList}));
//            cardGridLayout.addView(cardView);
//        }

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NumberViewModel = new ViewModelProvider(this).get(Number_viewmodel.class);
        LetterViewModel = new ViewModelProvider(this).get(Letter_viewmodel.class);
        cardGridLayout = v.findViewById(R.id.gridlayout);

        LetterViewModel.getLetters().observe(getViewLifecycleOwner(), letters -> {
            if (letters == null){
                letterArray = LetterViewModel.getLetters().getValue();
            }

            letterArray.forEach( letter -> {
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(letter);
                cardGridLayout.addView(cardView);
            });

            Fragment player1Frag = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.frag_player1);

            assert player1Frag != null;
            FragmentTransaction tr = getParentFragmentManager().beginTransaction();
            tr.replace(R.id.frag_player1, player1Frag);
            tr.commit();
        });

    }
}