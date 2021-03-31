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

public class Fragment_player2_Number extends Fragment {

    public GridLayout cardGridLayout;
    View v;
    Gamestate_viewmodel GameViewModel;

    public Fragment_player2_Number() {
        // Required empty public constructor
        super(R.layout.player1_number);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.player1_number, container, false);
        v.setRotation(180);
        v.requestLayout();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameViewModel = new ViewModelProvider(this).get(Gamestate_viewmodel.class);
        cardGridLayout = v.findViewById(R.id.gridlayout);
        Number_viewmodel numberViewModel = new ViewModelProvider(requireActivity()).get(Number_viewmodel.class);


        v.findViewById(R.id.btn_low_number).setOnClickListener(view -> {
            numberViewModel.pickLowNumber();
            Log.d("TAG", "LOW");
        });

        v.findViewById(R.id.btn_high_number).setOnClickListener(view -> {
            Log.d("TAG", "HIGH");
            numberViewModel.pickHighNumber();
        });



        numberViewModel.getNumbers().observe(getViewLifecycleOwner(), numberArray -> {
            Log.d("TAG", "onActivityCreated: TESTESTESTESTESTESS ");
            if (numberArray.size() > 0 && numberArray.size() <= 6){
                View cardView = getLayoutInflater().inflate(R.layout.cardlayout, cardGridLayout, false);
                TextView tv = cardView.findViewById(R.id.number_card_text);
                tv.setText(String.valueOf(numberArray.get(numberArray.size()-1)));
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