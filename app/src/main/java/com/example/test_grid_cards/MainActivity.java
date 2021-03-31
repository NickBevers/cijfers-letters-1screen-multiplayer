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
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Gamestate_viewmodel viewModel;
    private final Fragment_player1_Letter frag1Letter = new Fragment_player1_Letter();
    private final Fragment_player1_Number frag1Number = new Fragment_player1_Number();
    private final Fragment_player2_Letter frag2Letter = new Fragment_player2_Letter();
    private final Fragment_player2_Number frag2Number = new Fragment_player2_Number();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(Gamestate_viewmodel.class);

        viewModel.getRound().observe(this, round -> {
            if(round.equals(Gamestate_viewmodel.RoundNum)){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_player1, frag1Letter)
                        .replace(R.id.frag_player2, frag2Letter)
                        .commit();
            }
            else{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_player1, frag1Number)
                        .replace(R.id.frag_player2, frag2Number)
                        .commit();
            }
        });


    }
}

//IDEA: 2 extra fragments for the Letters/Numbers buttons