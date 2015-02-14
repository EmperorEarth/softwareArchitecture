package com.junjunguo.ponggame;

import android.content.Intent;

/**
 * Created by GuoJunjun on 09/02/15.
 */
public class GameController {

    private boolean singlePlayer;
    private boolean playSound;
    private boolean touchControl;
    private int challenging;
    public static final String EXTRAMESSAGE = "WINNER";

    /**
     * init game values before start
     */
    public void initGameModel() {
        //wall setting
        GameModel.setUpwall(50);
        GameModel.setDownwall(GameModel.getScreenY() - 100);
        GameModel.setLeftwall(30);
        GameModel.setRightwall(GameModel.getScreenX() - 30);

        // result:
        GameModel.setScoreUp(0);
        GameModel.setScoreDown(0);
        GameModel.setResultUp(GameModel.getScoreUp() + "");
        GameModel.setResultDown(GameModel.getScoreDown() + "");

        initRacketUp();
        initRacketDown();

        if (isSinglePlayer()) {
            initSinglePlayer();
        } else {
            initTwoPlayers();
        }
    }

    private void initSinglePlayer() {
        GameModel.setRacketUpSpeedX(0);
        GameModel.setRacketUpSpeedY(0);
        setBallSpeed(200 * getChallenging(), 200 * getChallenging());
    }

    private void initTwoPlayers() {
        GameModel.setRacketUpSpeedX(0);
        GameModel.setRacketUpSpeedY(0);
        //speed:
        touchControl = true;
    }


    public void twoPlayersBallSpeed(int total) {
        if (!isSinglePlayer()) {
            int directionx, directiony;
            if (GameModel.getSpeedX() >= 0) {
                directionx = 1;
            } else {
                directionx = -1;
            }
            if (GameModel.getSpeedY() >= 0) {
                directiony = 1;
            } else {
                directiony = -1;
            }
            setBallSpeed((200 * getChallenging() + total * 5) * directionx,
                    (200 * getChallenging() + total * 5) * directiony);
        }
    }

    public boolean isSinglePlayer() {
        return this.singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public boolean isPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    public int getChallenging() {
        return challenging;
    }

    public void setChallenging(int challenging) {
        this.challenging = challenging;
    }

    public boolean isTouchControl() {
        return touchControl;
    }

    public void setTouchControl(boolean touchControl) {
        this.touchControl = touchControl;
    }

    public void setBallSpeed(float x, float y) {
        GameModel.setSpeedX(x);
        GameModel.setSpeedY(y);
    }

    /**
     * start position
     */
    private void initRacketUp() {
        GameModel.setRacketUpx(GameModel.getScreenX() / 2);
        GameModel.setRacketUpy(50);
    }

    /**
     * start position
     */
    private void initRacketDown() {
        GameModel.setRacketDownx(GameModel.getScreenX() / 2);
        GameModel.setRacketDowny(GameModel.getScreenY() - 100);
    }

    /**
     * automatic move up side racket
     */
    public void autoUpRacket() {
        if (PongScreen.getBall().getSpeed().getY() < 0) {
            if (PongScreen.getRacketUp().getPosition().getX() < PongScreen.getBall().getPosition().getX()) {
                GameModel.setRacketUpSpeedX(60 * getChallenging());
                PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
            } else if (PongScreen.getRacketUp().getPosition().getX() > PongScreen.getBall().getPosition().getX()) {
                GameModel.setRacketUpSpeedX(-60 * getChallenging());
                PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
            } else {
                PongScreen.getRacketUp().setSpeed(0, 0);
            }
        } else if (PongScreen.getRacketUp().getPosition().getX() > GameModel.getScreenX() / 2) {
            GameModel.setRacketUpSpeedX(-60 * getChallenging());
            PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
        } else if (PongScreen.getRacketUp().getPosition().getX() < GameModel.getScreenX() / 2) {
            GameModel.setRacketUpSpeedX(60 * getChallenging());
            PongScreen.getRacketUp().setSpeed(GameModel.getRacketUpSpeedX(), GameModel.getRacketUpSpeedY());
        } else {
            PongScreen.getRacketUp().setSpeed(0, 0);
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
    public void hitWallHandler() {
        if (PongScreen.getBall().getPosition().getX() <= GameModel.getLeftwall() ||
                PongScreen.getBall().getPosition().getX() >= GameModel.getRightwall()) {
            GameModel.setSpeedX(-GameModel.getSpeedX());
        }
        if (PongScreen.getBall().getPosition().getY() >= GameModel.getDownwall()) {
            GameModel.setScoreUp(GameModel.getScoreUp() + 1);
            GameModel.setSpeedY(-GameModel.getSpeedY());
            GameModel.setResultUp(GameModel.getScoreUp() + "");
        } else if (PongScreen.getBall().getPosition().getY() <= GameModel.getUpwall()) {
            GameModel.setScoreDown(GameModel.getScoreDown() + 1);
            GameModel.setSpeedY(-GameModel.getSpeedY());
            GameModel.setResultDown(GameModel.getScoreDown() + "");
        }
        if (GameModel.getScoreDown() >= 21) {
            GameModel.setResultDown(GameModel.getScoreDown() + " WIN !");
            PongScreen.getBall().setSpeed(0, 0);
            PongScreen.getBall().setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);
            Intent intent = new Intent(PongScreen.getContext(), Result.class);
            intent.putExtra(EXTRAMESSAGE, GameModel.getScoreUp() + "\n\n" + GameModel.getScoreDown() + "\n\nWIN");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PongScreen.getContext().startActivity(intent);
        } else if (GameModel.getScoreUp() >= 21) {
            GameModel.setResultUp(" WIN !" + GameModel.getScoreUp());
            PongScreen.getBall().setSpeed(0, 0);
            PongScreen.getBall().setPosition(GameModel.getScreenX() / 2, GameModel.getScreenY() / 2);
            Intent intent = new Intent(PongScreen.getContext(), Result.class);
            intent.putExtra(EXTRAMESSAGE, "WIN\n\n" + GameModel.getScoreUp() + "\n\n:\n\n" + GameModel.getScoreDown());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PongScreen.getContext().startActivity(intent);
        }
    }

    /**
     * control up side racket by touch
     */
    public void racketupTouchController() {
        int utx = GameModel.getTouchUpx();
        int urx = (int) PongScreen.getRacketUp().getPosition().getX();

        if (urx > utx) {
            PongScreen.getRacketUp().setSpeed(-(Math.abs(GameModel.getSpeedX() * 2)), 0);
        } else if (urx < utx) {
            PongScreen.getRacketUp().setSpeed((Math.abs(GameModel.getSpeedX() * 2)), 0);
        } else {
            PongScreen.getRacketUp().setSpeed(0, 0);
        }
    }

    /**
     * control down side racket by touch
     */
    public void racketdownTouchController() {
        int dtx = GameModel.getTouchDownx();
        int drx = (int) PongScreen.getRacketDown().getPosition().getX();

        if (drx > dtx + 5) {
            GameModel.setRacketDownSpeedX(-(Math.abs(GameModel.getSpeedX() * 2)));
            PongScreen.getRacketDown().setSpeed(GameModel.getRacketDownSpeedX(), GameModel.getRacketDownSpeedY());
        } else if (drx < dtx - 5) {
            GameModel.setRacketDownSpeedX((Math.abs(GameModel.getSpeedX() * 2)));
            PongScreen.getRacketDown().setSpeed(GameModel.getRacketDownSpeedX(), GameModel.getRacketDownSpeedY());
        } else if (drx <= dtx + 5 || drx >= dtx - 5) {
            PongScreen.getRacketDown().setSpeed(0, 0);
        }
    }

    @Override public String toString() {
        return "GameController{" +
                "singlePlayer=" + singlePlayer +
                ", playSound=" + playSound +
                ", touchControl=" + touchControl +
                ", challenging=" + challenging +
                '}';
    }
}
