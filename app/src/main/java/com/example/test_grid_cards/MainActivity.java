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

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final Fragment_player1 frag1 = new Fragment_player1();
    private final Fragment_player2 frag2 = new Fragment_player2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_player1, frag1)
                .replace(R.id.frag_player2, frag2)
                .commit();
    }
}

//IDEA: 2 extra fragments for the Letters/Numbers buttons