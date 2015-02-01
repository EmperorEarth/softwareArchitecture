package com.junjunguo.pong;

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
 * Created by GuoJunjun on 26/01/15.
 */
public class PongScreen extends State implements SensorEventListener {
    private float speedX, speedY, upwall, downwall, leftwall, rightwall, racketUpx, racketUpy, racketDownx, racketDowny,
            racketSpeedX, racketSpeedY;
    private int scoreDown, scoreUp;
    private String resultUp, resultDown;
    private int screenX, screenY;
    private Paint paint;
    private Image ballImg = new Image(R.drawable.ball);
    private Image racketImg = new Image(R.drawable.rocket);
    private Image bgImg = new Image(R.drawable.bg);
    private Sprite racketUp, racketDown, ball, bg;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerator;

    public PongScreen(int screenX, int screenY, Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensorAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
        System.out.println("screen size: X" + screenX + " Y " + screenY);
        this.screenX = screenX;
        this.screenY = screenY;
        racketUpx = screenX / 2;
        racketUpy = 50;//screenY / 2;
        racketDowny = screenY - 100;//screenY - 100;
        racketDownx = screenX / 2;//screenX / 2;

        bg = new Sprite(bgImg);
        racketUp = new Sprite(racketImg);
        racketDown = new Sprite(racketImg);
        ball = new Sprite(ballImg);

        racketUp.setPosition(racketUpx, racketUpy);
        racketDown.setPosition(racketDownx, racketDowny);
        ball.setPosition(screenX / 2, screenY / 2);

        //start speed:
        racketSpeedX = 280;
        racketSpeedY = 0;
        speedX = 300;
        speedY = 300;
        ball.setSpeed(speedX, speedY);
        racketUp.setSpeed(0, 0);
        racketDown.setSpeed(0, 0);
        //        startPong();

        // set wall values:
        upwall = 50;
        downwall = screenY - 100;
        leftwall = 30;
        rightwall = screenX - 30;

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


        canvas.drawText(resultUp, 20, screenY / 2 - 50, paint);
        canvas.drawText(resultDown, 20, screenY / 2 + 50, paint);
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
     * automatic move up side racket
     */
    private void autoUpRacket() {
        if (ball.getSpeed().getY() < 0) {
            if (racketUp.getPosition().getX() < ball.getPosition().getX()) {
                racketUp.setSpeed(racketSpeedX, racketSpeedY);
            } else if (racketUp.getPosition().getX() > ball.getPosition().getX()) {
                racketUp.setSpeed(-racketSpeedX, racketSpeedY);
            } else {
                racketUp.setSpeed(0, 0);
            }
        } else if (racketUp.getPosition().getX() > screenX / 2) {
            racketUp.setSpeed(-racketSpeedX, racketSpeedY);
        } else if (racketUp.getPosition().getX() < screenX / 2) {
            racketUp.setSpeed(racketSpeedX, racketSpeedY);
        } else {
            racketUp.setSpeed(0, 0);
        }
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
            ball.setPosition(screenX / 2, screenY / 2);
            //            startPong();
        } else if (scoreUp >= 21) {
            resultUp = " WIN !" + scoreUp;
            ball.setSpeed(0, 0);
            ball.setPosition(screenX / 2, screenY / 2);
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

    /**
     * start the game:
     */
    //    public void startPong() {
    //        addTouchListener(new TouchListener() {
    //            @Override public boolean onTouchDown(MotionEvent motionEvent) {
    //                if (motionEvent.getX() > screenX / 2) {
    //                    speedX = 100;
    //                    speedY = 10;
    //                } else if (motionEvent.getX() < screenX / 2) {
    //                    speedX = -100;
    //                    speedY = 10;
    //                }
    //                return false;
    //            }
    //
    //            @Override public boolean onTouchUp(MotionEvent motionEvent) {
    //                return false;
    //            }
    //
    //            @Override public boolean onTouchMove(MotionEvent motionEvent) {
    //                return false;
    //            }
    //        });
    //    }
}

