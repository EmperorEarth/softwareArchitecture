package com.junjunguo.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

/**
 * Created by GuoJunjun on 10/02/15.
 */
public class PongScreen extends State implements SensorEventListener {

    private Paint paint;
    private Image ballImg = new Image(R.drawable.ball);
    private Image racketImg = new Image(R.drawable.rocket);
    private Image bgImg = new Image(R.drawable.bg);
    private static Sprite racketUp;
    private static Sprite racketDown;


    private static Sprite ball;
    private static Sprite bg;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerator;

    public PongScreen(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensorAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
        System.out.println("screen size: X" + GameModel.getScreenX() + " Y " + GameModel.getScreenY());

        bg = new Sprite(bgImg);
        racketUp = new Sprite(racketImg);
        racketDown = new Sprite(racketImg);
        ball = new Sprite(ballImg);

        racketUp.setPosition(GameModel.getRacketUpx(), GameModel.getRacketUpy());
        racketDown.setPosition(GameModel.getRacketDownx(), GameModel.getRacketDowny());
        ball.setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);

        //start speed:
        speedX = 300;
        speedY = 300;
        ball.setSpeed(speedX, speedY);
        racketUp.setSpeed(0, 0);
        racketDown.setSpeed(0, 0);
        //        startPong();

        // set wall values:
        upwall = 50;
        downwall = GameModel.getScreenY() - 100;
        leftwall = 30;
        rightwall = GameModel.getScreenX() - 30;

        // show result:
        scoreUp = 0;
        scoreDown = 0;

        resultUp = scoreUp + "";
        resultDown = scoreDown + "";
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        paint.setColor(Color.rgb(208, 255, 20));

        // racket control
    }

    public void draw(Canvas canvas) {
        bg.draw(canvas);
        racketUp.draw(canvas);
        racketDown.draw(canvas);
        ball.draw(canvas);


        canvas.drawText(resultUp, 20, GameModel.getScreenY() / 2 - 50, paint);
        canvas.drawText(resultDown, 20, GameModel.getScreenY() / 2 + 50, paint);
    }

    public void update(float dt) {
        ball.update(dt);
        racketUp.update(dt);
        racketDown.update(dt);
        racketHandler();
        hitWallHandler();
        ball.setSpeed(speedX, speedY);

        System.out.println(ball.getSpeed().getX() + " X Y " + ball.getSpeed().getY());
        //        ball.setSpeed(speedX, speedY);

        // RESULT:
    }


    private void racketHandler() {
        if (racketUp.collides(ball)) {
            speedY = -speedY;
            ball.setSpeed(speedX, speedY * (racketDown.getSpeed().getX() / 10));
        } else if (racketDown.collides(ball)) {
            speedY = -speedY;
            ball.setSpeed(speedX, speedY * (racketDown.getSpeed().getX() / 10));
        }
        autoUpRacket();
    }


    /**
     * handler the ball if hits the up, down, right left walls
     * <p/>
     * up score if the ball hit the down wall
     * <p/>
     * down score if the ball hit the up wall
     * <p/>
     * stop the game by set ball speed to 0, and set ball position at the middle of the screen if one side wins
     */
    private void hitWallHandler() {
        if (ball.getPosition().getX() <= leftwall || ball.getPosition().getX() >= rightwall) {
            speedX = -speedX;
        }
        if (ball.getPosition().getY() >= downwall) {
            scoreUp++;
            speedY = -speedY;
            resultUp = scoreUp + "";
        } else if (ball.getPosition().getY() <= upwall) {
            scoreDown++;
            speedY = -speedY;
            resultDown = scoreDown + "";
        }
        if (scoreDown >= 21) {
            resultDown = scoreDown + " WIN !";
            ball.setSpeed(0, 0);
            ball.setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);
            //            startPong();
        } else if (scoreUp >= 21) {
            resultUp = " WIN !" + scoreUp;
            ball.setSpeed(0, 0);
            ball.setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);
            //            startPong();
        }
    }

    @Override public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float z = event.values[2];

        float ax, az, anglexz;
        ax = x;
        az = z;

        anglexz = (float) (Math.atan2(ax, az) / (Math.PI / 180));
        // rocket control :
        float angle = anglexz;
        if (racketDown.getPosition().getX() <= leftwall - 10) {
            if (angle > 0) {
                racketDown.setSpeed(30 * angle, 0);
            } else {
                racketDown.setSpeed(0, 0);
            }
        } else if (racketDown.getPosition().getX() >= rightwall + 10) {
            if (angle < 0) {
                racketDown.setSpeed(30 * angle, 0);
            } else {
                racketDown.setSpeed(0, 0);
            }
        } else {
            racketDown.setSpeed(30 * angle, 0);
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public static Sprite getBall() {
        return ball;
    }

    public static void setBall(Sprite ball) {
        PongScreen.ball = ball;
    }

    public static Sprite getRacketDown() {
        return racketDown;
    }

    public static void setRacketDown(Sprite racketDown) {
        PongScreen.racketDown = racketDown;
    }

    public static Sprite getRacketUp() {
        return racketUp;
    }

    public static void setRacketUp(Sprite racketUp) {
        PongScreen.racketUp = racketUp;
    }
}


