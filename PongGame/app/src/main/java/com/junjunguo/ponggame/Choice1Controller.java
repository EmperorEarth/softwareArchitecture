package com.junjunguo.ponggame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import sheep.game.Game;


public class Choice1Controller extends Activity {
    private int challenging;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice1view);
        challenging = 1;
        textView = (TextView) findViewById(R.id.game_challenging_value);
    }

    public void startGameC1(View view) {
        MainModel.setBallSpeed(100 * challenging, 100 * challenging);
        activeGameScreen();
    }

    public void challengingValueUp(View view) {
        if (challenging < 9) {
            challenging++;
            textView.setText(challenging);
        }
    }

    public void challengingValueDown(View view) {
        if (challenging > 1) {
            challenging--;
            textView.setText(challenging);
        }

    }

    private void activeGameScreen() {
        Game game = SingletonGame.getInstance(this, null);
        game.pushState(new PongScreen(getApplicationContext()));
        setContentView(game);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice1_controller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
