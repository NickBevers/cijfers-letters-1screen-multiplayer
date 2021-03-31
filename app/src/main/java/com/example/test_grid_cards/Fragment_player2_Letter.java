package com.example.test_grid_cards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class Fragment_player2_Letter extends Fragment {

    public GridLayout cardGridLayout;
    View v;
    Gamestate_viewmodel GameViewModel;

    public Fragment_player2_Letter() {
        // Required empty public constructor
        super(R.layout.player1_number);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.player1_letter, container, false);
        v.setRotation(180);
        v.requestLayout();
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
            Log.d("TAG", "VOWELVOWELVOWEL");
        });

        v.findViewById(R.id.btn_consonant).setOnClickListener(view -> {
            Log.d("TAG", "CONSONANTCONSONANT");
            letterViewModel.pickConsonant();
        });


        /*GameViewModel.getRound().observe(getViewLifecycleOwner(), round -> {
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
        });*/



        letterViewModel.getLetters().observe(getViewLifecycleOwner(), letterArray -> {
            Log.d("TAG", "onActivityCreated: TESTESTESTESTESTESS ");
            if (letterArray.size() > 0 && letterArray.size() <= 6){
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(String.valueOf(letterArray.get(letterArray.size()-1)));
                cardGridLayout.addView(cardView);
            }
        });
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