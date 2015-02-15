/*
 * Created by GuoJunjun <junjunguo.com> on 15.2.2015.
 *
 * This file is part of PongGame
 */

package com.junjunguo.ponggame;


public class MainModel {
    private static GameController instance = null;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }
}