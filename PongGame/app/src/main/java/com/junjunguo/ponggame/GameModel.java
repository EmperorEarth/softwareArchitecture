package com.junjunguo.ponggame;

/**
 * Created by GuoJunjun on 10/02/15.
 */
public class GameModel {
    private static float speedX, speedY, upwall, downwall, leftwall, rightwall, racketUpx, racketUpy, racketDownx,
            racketDowny, racketUpSpeedX, racketUpSpeedY, racketDownSpeedX, racketDownSpeedY;
    private static int scoreDown, scoreUp;
    private static String resultUp, resultDown;
    private static int screenX, screenY;

    public static float getSpeedX() {
        return speedX;
    }

    public static void setSpeedX(float speedX) {
        GameModel.speedX = speedX;
    }

    public static float getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(float speedY) {
        GameModel.speedY = speedY;
    }

    public static float getUpwall() {
        return upwall;
    }

    public static void setUpwall(float upwall) {
        GameModel.upwall = upwall;
    }

    public static float getDownwall() {
        return downwall;
    }

    public static void setDownwall(float downwall) {
        GameModel.downwall = downwall;
    }

    public static float getLeftwall() {
        return leftwall;
    }

    public static void setLeftwall(float leftwall) {
        GameModel.leftwall = leftwall;
    }

    public static float getRightwall() {
        return rightwall;
    }

    public static void setRightwall(float rightwall) {
        GameModel.rightwall = rightwall;
    }

    public static float getRacketUpx() {
        return racketUpx;
    }

    public static void setRacketUpx(float racketUpx) {
        GameModel.racketUpx = racketUpx;
    }

    public static float getRacketUpy() {
        return racketUpy;
    }

    public static void setRacketUpy(float racketUpy) {
        GameModel.racketUpy = racketUpy;
    }

    public static float getRacketDownx() {
        return racketDownx;
    }

    public static void setRacketDownx(float racketDownx) {
        GameModel.racketDownx = racketDownx;
    }

    public static float getRacketDowny() {
        return racketDowny;
    }

    public static void setRacketDowny(float racketDowny) {
        GameModel.racketDowny = racketDowny;
    }

    public static float getRacketUpSpeedX() {
        return racketUpSpeedX;
    }

    public static void setRacketUpSpeedX(float racketUpSpeedX) {
        GameModel.racketUpSpeedX = racketUpSpeedX;
    }

    public static float getRacketUpSpeedY() {
        return racketUpSpeedY;
    }

    public static void setRacketUpSpeedY(float racketUpSpeedY) {
        GameModel.racketUpSpeedY = racketUpSpeedY;
    }

    public static float getRacketDownSpeedX() {
        return racketDownSpeedX;
    }

    public static void setRacketDownSpeedX(float racketDownSpeedX) {
        GameModel.racketDownSpeedX = racketDownSpeedX;
    }

    public static float getRacketDownSpeedY() {
        return racketDownSpeedY;
    }

    public static void setRacketDownSpeedY(float racketDownSpeedY) {
        GameModel.racketDownSpeedY = racketDownSpeedY;
    }

    public static int getScoreDown() {
        return scoreDown;
    }

    public static void setScoreDown(int scoreDown) {
        GameModel.scoreDown = scoreDown;
    }

    public static int getScoreUp() {
        return scoreUp;
    }

    public static void setScoreUp(int scoreUp) {
        GameModel.scoreUp = scoreUp;
    }

    public static String getResultUp() {
        return resultUp;
    }

    public static void setResultUp(String resultUp) {
        GameModel.resultUp = resultUp;
    }

    public static String getResultDown() {
        return resultDown;
    }

    public static void setResultDown(String resultDown) {
        GameModel.resultDown = resultDown;
    }

    public static int getScreenX() {
        return screenX;
    }

    public static void setScreenX(int screenX) {
        GameModel.screenX = screenX;
    }

    public static int getScreenY() {
        return screenY;
    }

    public static void setScreenY(int screenY) {
        GameModel.screenY = screenY;
    }
}
