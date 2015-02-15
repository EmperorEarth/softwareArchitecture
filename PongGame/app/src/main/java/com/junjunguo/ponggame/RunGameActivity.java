/*
 * Created by GuoJunjun <junjunguo.com> on 15.2.2015.
 *
 * This file is part of PongGame
 */

package com.junjunguo.ponggame;

import android.app.Activity;
import android.os.Bundle;

import sheep.game.Game;


public class RunGameActivity extends Activity {

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
