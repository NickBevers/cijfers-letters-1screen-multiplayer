/*
Needed:

Main activity -> The screen

Fragment Player 1 -> bottom half of the screen
Fragment Player 2 -> top half of the screen

Viewmodel Letters -> All content for the letters
Viewmodel Numbers -> All content for the numbers
Viewmodel Gamestate -> All info about the game (playing/stopped, round,
 */


package com.example.test_grid_cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Gamestate_viewmodel viewModel;
    private final Fragment_player1 frag1 = new Fragment_player1();
    private final Fragment_player2 frag2 = new Fragment_player2();
    private final LetterFrag letter_frag1 = new LetterFrag();
    private final LetterFrag letter_frag2 = new LetterFrag();
    private final NumberFrag number_frag1 = new NumberFrag();
    private final NumberFrag number_frag2 = new NumberFrag();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(Gamestate_viewmodel.class);

        viewModel.getRound().observe(this, round -> {
            if(!round.equals(Gamestate_viewmodel.RoundNum)){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_player1, frag1)
                        .replace(R.id.ButtonPlayer1, number_frag1)
                        .replace(R.id.frag_player2, frag2)
                        .replace(R.id.ButtonPlayer2, number_frag2)
                        .commit();
            }
            else{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_player1, frag1)
                        .replace(R.id.ButtonPlayer1, letter_frag1)
                        .replace(R.id.frag_player2, frag2)
                        .replace(R.id.ButtonPlayer2, letter_frag2)
                        .commit();
            }
        });
    }
}

//IDEA: 2 extra fragments for the Letters/Numbers buttons