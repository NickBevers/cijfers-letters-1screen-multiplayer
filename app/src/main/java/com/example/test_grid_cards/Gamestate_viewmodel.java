package com.example.test_grid_cards;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Gamestate_viewmodel extends ViewModel{
    private MutableLiveData<Integer> round;
    private MutableLiveData<Integer> game;
    public static final Integer RoundNum = 0;
    public static final Integer GameType = 0;

    public MutableLiveData<Integer> getRound() {
        if (round == null) {
            round = new MutableLiveData<Integer> ();
            round.setValue(RoundNum);
        }
        return round;
    }

    public MutableLiveData<Integer> getGame() {
        if (game == null) {
            game = new MutableLiveData<Integer> ();
            game.setValue(GameType);
        }
        return game;
    }
}
