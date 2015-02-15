/*
 * Created by GuoJunjun <junjunguo.com> on 15.2.2015.
 *
 * This file is part of PongGame
 */

package com.junjunguo.ponggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Result extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void startNewGame(View v) {
        startActivity(new Intent(this, StartController.class));
        finish();
    }

    public void quitGame(View v) {
        Intent intent = new Intent(getApplicationContext(), StartController.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    @Override protected void onResume() {
        super.onResume();
        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.score_result);
        textView.setText(intent.getStringExtra(GameController.EXTRAMESSAGE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
