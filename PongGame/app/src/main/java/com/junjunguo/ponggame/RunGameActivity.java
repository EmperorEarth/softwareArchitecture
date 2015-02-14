package com.junjunguo.ponggame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import sheep.game.Game;


public class RunGameActivity extends ActionBarActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_game);
    }

    @Override protected void onResume() {
        super.onResume();
        //        game = SingletonGame.getInstance(this, null);
        game = new Game(this, null);
        PongScreen pongScreen = new PongScreen(getApplicationContext());
        game.pushState(pongScreen);
        setContentView(game);
    }

    @Override protected void onPause() {
        super.onPause();
        try {
            game.popState();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
