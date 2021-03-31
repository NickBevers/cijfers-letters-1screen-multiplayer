package com.example.test_grid_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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

    public GridLayout cardGridLayout;
    View v;
    Gamestate_viewmodel GameViewModel;
    private final LetterFrag letter_frag = new LetterFrag();
    private final NumberFrag number_frag = new NumberFrag();

    public Fragment_player1() {
        // Required empty public constructor
        super(R.layout.activity_fragment_player1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_fragment_player1, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GameViewModel = new ViewModelProvider(this).get(Gamestate_viewmodel.class);
        GameViewModel.getRound().observe(getViewLifecycleOwner(), round -> {
            if(!round.equals(Gamestate_viewmodel.RoundNum)){
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.child_fragment, number_frag)
                        .commit();
            }
            else{
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.child_fragment, letter_frag)
                        .commit();
            }
        });

        cardGridLayout = v.findViewById(R.id.gridlayout);
        Letter_viewmodel letterViewModel = new ViewModelProvider(requireActivity()).get(Letter_viewmodel.class);
        Number_viewmodel numberViewModel = new ViewModelProvider(requireActivity()).get(Number_viewmodel.class);

        letterViewModel.getLetters().observe(getViewLifecycleOwner(), letters -> {

            Log.d("TAG", "onActivityCreated: TESTESTESTESTESTESS ");
            for (int i = 0; i < letters.size(); i++){
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText("letters.get(i)");
                cardGridLayout.addView(cardView);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /*letterViewModel.getLetters().observe(getViewLifecycleOwner(), letters -> {
            Log.d("TAG", "letterArray: " + Arrays.toString(new ArrayList[]{letters}));
            for (int i = 0; i < letters.size(); i++) {
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(letters.get(i));
                cardGridLayout.addView(cardView);
            }
        });*/


    }


}

/*
TextView tv = cardView.findViewById(R.id.number_card_text);
tv.setText(Integer.toString(i +1));
 */

/*
LetterViewModel.getLetters().observe(getViewLifecycleOwner(), letters -> {
    letters.forEach( letter -> {
        TextView tv = letter.findViewById(R.id.number_card_text);
        tv.setText(letter);
        cardGridLayout.addView(cardView);
    });

    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
            .detach(this)
            .attach(this)
            .commit();
});
 */

/*
for (int i = 0; i < letterArray.size(); i++){
    String a = String.valueOf(letterArray.get(i));
    View tvListItem = tvList.get(i);
    Log.d("TVListarray", "arr: " + Arrays.toString(new View[]{tvListItem}));
    //tvList.set(i, a);
}
 */


/*
@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardGridLayout = v.findViewById(R.id.gridlayout);
        Letter_viewmodel LetterViewModel = new ViewModelProvider(requireActivity()).get(Letter_viewmodel.class);
        Number_viewmodel NumberViewModel = new ViewModelProvider(requireActivity()).get(Number_viewmodel.class);

        for (int i = 0; i < 6; i++){
            View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
            TextView tv = cardView.findViewById(R.id.number_card_text);
            tv.setText("");
            cardGridLayout.addView(cardView);
        }


        LetterViewModel.getLetters().observe(getViewLifecycleOwner(), letters -> {
                Log.d("TAG", "letterArray: " + Arrays.toString(new ArrayList[]{letters}));
                for (int i = 0; i < letters.size(); i++) {
        View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
        TextView tv = cardView.findViewById(R.id.number_card_text);
        tv.setText(letters.get(i));
        cardGridLayout.addView(cardView);
        }
        });


        }
 */