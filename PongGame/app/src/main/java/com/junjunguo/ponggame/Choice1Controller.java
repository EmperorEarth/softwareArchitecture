package com.junjunguo.ponggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class Choice1Controller extends Activity {
    private int challenging;
    private TextView textView;
    private GameController gameController;
    private RadioButton touch;
    private RadioButton sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice1view);
        challenging = 1;
        textView = (TextView) findViewById(R.id.game_challenging_value);
        gameController = MainModel.getInstance();
        touch = (RadioButton) findViewById(R.id.radiobtn_touch);
        sensor = (RadioButton) findViewById(R.id.radiobtn_sensor);
    }

    public void startGameC1(View view) {
        activeGameScreen();
    }

    public void challengingValueUp(View view) {
        if (challenging < 9) {
            challenging++;
            textView.setText(challenging + "");
        }
    }

    public void challengingValueDown(View view) {
        if (challenging > 1) {
            challenging--;
            textView.setText(challenging + "");
        }
    }

    /**
     * start to run game and move to game screen
     */
    private void activeGameScreen() {
        gameController.setSinglePlayer(true);
        gameController.setTouchControl(touch.isChecked());
        gameController.setChallenging((challenging + 1) / 2);

        Intent intent = new Intent(this, RunGameActivity.class);
        startActivity(intent);
    }

    /**
     * choose sensor or touch control
     *
     * @param view
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobtn_sensor:
                if (checked) {
                    touch.setChecked(false);
                    sensor.setChecked(true);
                    break;
                }
            case R.id.radiobtn_touch:
                if (checked) {
                    touch.setChecked(true);
                    sensor.setChecked(false);
                    break;
                }
        }
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
