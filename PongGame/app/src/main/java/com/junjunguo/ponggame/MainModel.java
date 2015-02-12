package com.junjunguo.ponggame;

/**
 * Created by GuoJunjun on 09/02/15.
 */
public class MainModel {
    private static GameController instance = null;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }
}