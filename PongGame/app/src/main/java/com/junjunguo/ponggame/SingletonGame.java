/*
 * Created by GuoJunjun <junjunguo.com> on 15.2.2015.
 *
 * This file is part of PongGame
 */

package com.junjunguo.ponggame;

import android.content.Context;
import android.util.AttributeSet;

import sheep.game.Game;

/**
 * Created by GuoJunjun on 10/02/15.
 * <p/>
 * <p/>
 * Singleton pattern
 * <p/>
 * <li> In software engineering, singleton pattern is a design pattern that restricts the instantiation of a class to
 * one object. </li><li> useful when exactly one object is needed to coordinate actions across the system. </li><li>
 * used when system operate more efficiently if only one object exists. </li><li> The Abstract Factory pattern can use
 * Singletons in its implementation.</li>
 * <p/>
 * <p/>
 * Implementation:
 * <p/>
 * <li> must satisfy single instance and global access. </li><li> can only create one object. </li><li> creating a class
 * with a method that creates a new instance of the class if one does not exist. </li><li> return a reference to the
 * object, if an instance already exists.</li>
 */
public class SingletonGame {
    private static Game instance = null;

    private SingletonGame() {
    }

    public static Game getInstance(Context context, AttributeSet attributeSet) {
        if (instance == null) {
            instance = new Game(context, attributeSet);
        }
        return instance;
    }
}
