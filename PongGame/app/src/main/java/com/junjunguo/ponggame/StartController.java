package com.junjunguo.ponggame;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class StartController extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startview);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        GameModel.setScreenX(size.x);
        GameModel.setScreenY(size.y);
    }

    /**
     * active 1 player when button is clicked
     *
     * @param view
     */
    public void active1player(View view) {
        movetooneplayerscreen();
    }

    /**
     * move to choiece1View
     */
    private void movetooneplayerscreen() {
        Intent intent = new Intent(this, Choice1Controller.class);
        startActivity(intent);
    }

    /**
     * active 2 player when button is clicked
     *
     * @param view
     */
    public void active2player(View view) {
        movetotwoplayerscreen();

    }

    /**
     * move to choice2view
     */
    private void movetotwoplayerscreen() {
        Intent intent = new Intent(this, Choice2Controller.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
