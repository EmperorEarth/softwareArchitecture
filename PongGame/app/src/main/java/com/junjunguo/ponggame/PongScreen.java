package com.junjunguo.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by GuoJunjun on 10/02/15.
 */
public class PongScreen extends State implements SensorEventListener {
    private Context context;
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
    private GameController gameController;
    private static int racketDownHitCounter = 0;

    public PongScreen(Context context) {
        this.context = context;
        gameController = MainModel.getInstance();
        gameController.initGameModel();
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensorAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
        bg = new Sprite(bgImg);
        racketUp = new Sprite(racketImg);
        racketDown = new Sprite(racketImg);
        ball = new Sprite(ballImg);
        racketUp.setPosition(GameModel.getRacketUpx(), GameModel.getRacketUpy());
        racketDown.setPosition(GameModel.getRacketDownx(), GameModel.getRacketDowny());
        ball.setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);
        ball.setSpeed(GameModel.getSpeedX(), GameModel.getSpeedY());
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        paint.setColor(Color.rgb(208, 255, 20));
        if (gameController.isTouchControl()) {
            activeTouchControll();
        }
        System.out.println("\n\n\n" + gameController.toString());
        System.out.println("\n\n\n" + GameModel.stringValues());
    }

    public void draw(Canvas canvas) {
        bg.draw(canvas);
        racketUp.draw(canvas);
        racketDown.draw(canvas);
        ball.draw(canvas);
        canvas.drawText(GameModel.getResultUp(), 20, GameModel.getScreenY() / 2 - 50, paint);
        canvas.drawText(GameModel.getResultDown(), 20, GameModel.getScreenY() / 2 + 50, paint);
    }

    public void update(float dt) {
        ball.update(dt);
        racketUp.update(dt);
        racketDown.update(dt);
        racketBallHandler();
        gameController.hitWallHandler();
        ball.setSpeed(GameModel.getSpeedX(), GameModel.getSpeedY());
        if (gameController.isSinglePlayer()) {
            gameController.autoUpRacket();
        }
        if (gameController.isTouchControl()) {
            gameController.racketdownTouchController();
            if (!gameController.isSinglePlayer()) {
                gameController.racketupTouchController();
            }
        }
    }

    /**
     * @return amount of time racket down hit the ball
     */
    public static int getRacketDownHitCounter() {
        return racketDownHitCounter;
    }

    private void racketBallHandler() {
        if (racketUp.collides(ball)) {
            GameModel.setSpeedY(-GameModel.getSpeedY());
            ball.setSpeed(GameModel.getSpeedX(), GameModel.getSpeedY() * (racketDown.getSpeed().getX() / 10));
        } else if (racketDown.collides(ball)) {
            racketDownHitCounter++;
            gameController.twoPlayersBallSpeed(getRacketDownHitCounter());
            GameModel.setSpeedY(-GameModel.getSpeedY());
            ball.setSpeed(GameModel.getSpeedX(), GameModel.getSpeedY() * (racketDown.getSpeed().getX() / 10));
        }
    }

    @Override public void onSensorChanged(SensorEvent event) {
        if (!gameController.isTouchControl()) {
            float y = event.values[1];
            float z = event.values[2];

            float angle = (float) (Math.atan2(y, z) / (Math.PI / 180));
            // rocket control : move speed according to angle rate bigger angle mover faster (angle rate not beyond 10)
            float anglerate = (Math.abs(angle) < 10 ? angle : (angle > 0 ? 10 : -10));
            GameModel.setRacketDownSpeedX(20 * gameController.getChallenging() * anglerate);
            if (racketDown.getPosition().getX() <= GameModel.getLeftwall() - 10) {
                if (angle > 0) {
                    racketDown.setSpeed(GameModel.getRacketDownSpeedX(), GameModel.getRacketDownSpeedY());
                } else {
                    racketDown.setSpeed(0, 0);
                }
            } else if (racketDown.getPosition().getX() >= GameModel.getRightwall() + 10) {
                if (angle < 0) {
                    racketDown.setSpeed(GameModel.getRacketDownSpeedX(), GameModel.getRacketDownSpeedY());
                } else {
                    racketDown.setSpeed(0, 0);
                }
            } else {
                racketDown.setSpeed(GameModel.getRacketDownSpeedX(), GameModel.getRacketDownSpeedY());
            }
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public static Sprite getBall() {
        return ball;
    }

    public static Sprite getRacketUp() {
        return racketUp;
    }

    public static Sprite getRacketDown() {
        return racketDown;
    }

    private void activeTouchControll() {
        addTouchListener(new TouchListener() {
            @Override public boolean onTouchDown(MotionEvent event) {
                int x, y;
                x = (int) event.getX();
                y = (int) event.getY();
                if (y < GameModel.getUpwall() + 10) {
                    GameModel.setTouchUpx(x);
                } else if (y > GameModel.getDownwall() - 10) {
                    GameModel.setTouchDownx(x);
                }
                return false;
            }

            @Override public boolean onTouchUp(MotionEvent event) {
                return false;
            }

            @Override public boolean onTouchMove(MotionEvent event) {
                return false;
            }
        });
    }

}


