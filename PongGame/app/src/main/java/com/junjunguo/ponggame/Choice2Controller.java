package com.junjunguo.ponggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Choice2Controller extends Activity {
    private int challengingT = 1;
    private TextView textView;
    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice2view);
        textView = (TextView) findViewById(R.id.game2_challenging_value);
        gameController = MainModel.getInstance();
    }

    public void startGameC2(View view) {
        activeGameScreen();
    }

    private void activeGameScreen() {
        gameController.setSinglePlayer(false);
        gameController.setTouchControl(true);
        gameController.setChallenging((challengingT + 1) / 2);
        Intent intent = new Intent(this, RunGameActivity.class);
        startActivity(intent);
    }

    public void challengingValueUp(View view) {
        if (challengingT < 6) {
            challengingT++;
            textView.setText(challengingT + "");
        }
    }

    public void challengingValueDown(View view) {
        if (challengingT > 1) {
            challengingT--;
            textView.setText(challengingT + "");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice2_controller, menu);
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
