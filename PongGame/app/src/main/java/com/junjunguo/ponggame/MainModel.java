package com.junjunguo.ponggame;

/**
 * Created by GuoJunjun on 09/02/15.
 */
public class MainModel {
    private static boolean singlePlayer;
    private static boolean playSound;
    private static boolean touchControl;


    public static boolean isSinglePlayer() {
        return singlePlayer;
    }

    public static void setSinglePlayer(boolean singlePlayer) {
        // if single player: up racket auto
        initRacketUp();
        initRacketDown();
        MainModel.singlePlayer = singlePlayer;
        if (singlePlayer) {
            GameModel.setRacketUpSpeedX(280);
            GameModel.setRacketUpSpeedY(0);

            autoUpRacket();
        }
    }

    public static boolean isPlaySound() {
        return playSound;
    }

    public static void setPlaySound(boolean playSound) {
        MainModel.playSound = playSound;
    }

    public static boolean isTouchControl() {
        return touchControl;
    }

    public static void setTouchControl(boolean touchControl) {
        MainModel.touchControl = touchControl;
    }

    public static void setBallSpeed(float x, float y) {
        GameModel.setSpeedX(x);
        GameModel.setSpeedY(y);
    }

    /**
     * start position
     */
    private static void initRacketUp() {
        GameModel.setRacketUpx(GameModel.getScreenX() / 2);
        GameModel.setRacketUpy(50);
    }

    /**
     * start position
     */
    private static void initRacketDown() {
        GameModel.setRacketDownx(GameModel.getScreenX() / 2);
        GameModel.setRacketDowny(GameModel.getScreenY() - 100);
    }


    /**
     * automatic move up side racket
     */
    private static void autoUpRacket() {
        if (PongScreen.getBall().getSpeed().getY() < 0) {
            if (PongScreen.getRacketUp().getPosition().getX() < PongScreen.getBall().getPosition().getX()) {
                PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
            } else if (PongScreen.getRacketUp().getPosition().getX() > PongScreen.getBall().getPosition().getX()) {
                PongScreen.getRacketUp().setSpeed(-GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
            } else {
                PongScreen.getRacketUp().setSpeed(0, 0);
            }
        } else if (PongScreen.getRacketUp().getPosition().getX() > GameModel.getScreenX() / 2) {
            PongScreen.getRacketUp().setSpeed(-GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
        } else if (PongScreen.getRacketUp().getPosition().getX() < GameModel.getScreenX() / 2) {
            PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
        } else {
            PongScreen.getRacketUp().setSpeed(0, 0);
        }
    }
}