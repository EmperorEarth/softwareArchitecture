package com.junjunguo.gameintroduction;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by GuoJunjun on 22.01.15.
 */
public class TitleScreen extends State {

    private Image heliR = new Image(R.drawable.helicopterright);
    private Image heliL = new Image(R.drawable.helicopterleft);

    private Image wallVerticalImg = new Image(R.drawable.wall_vertical);
    private Image bgImage = new Image(R.drawable.bg800x1200);
    private Sprite asprite, westWall, backSprite, bsprite;
    private Paint postext;
    private String string = "";
    private int x, y;

    public TitleScreen() {
        backSprite = new Sprite(bgImage);
        bsprite = new Sprite(heliR);
        asprite = new Sprite(heliL);
        westWall = new Sprite(wallVerticalImg);
        westWall.setPosition(4, 215);
        asprite.setPosition(300, 120);
        bsprite.setPosition(200, 420);
//        bsprite.setSpeed(50, 70);


        postext = new Paint();
        postext.setStyle(Paint.Style.FILL);
        postext.setTextSize(20);
        postext.setColor(Color.RED);

        addTouchListener(new TouchListener() {
            @Override public boolean onTouchDown(MotionEvent event) {
                x = (int) event.getX();
                y = (int) event.getY();
                return false;
            }

            @Override public boolean onTouchUp(MotionEvent event) {
                //                asprite.setSpeed(0, -80);
                return false;
            }

            @Override public boolean onTouchMove(MotionEvent event) {
                return false;
            }
        });
    }

    /**
     * TASK 2: control the movement
     */
    private void moveto() {
        int ax = (int) asprite.getX();
        int ay = (int) asprite.getY();
        float sx, sy;
        //        System.out.println("ax: " + ax + " x: " + x + " ay: " + ay);
        if (ax - x > 0) {
            sx = -80;
        } else if (ax - x < 0) {
            sx = 80;
        } else {
            sx = 0;
        }
        if (ay - y > 0) {
            sy = -90;
        } else if (ay - y < 0) {
            sy = 90;
        } else {
            sy = 0;
        }

        asprite.setSpeed(sx, sy);
    }

    private int sx = 90, sy = 90;

    private void randomMove(Sprite thesprite) {
        int borderX = 600;
        int borderY = 1000;
        int ax = (int) thesprite.getX();
        int ay = (int) thesprite.getY();

        if (ax >= borderX) {
            sx = (int) -(Math.random() * 100 + 50);
        } else if (ax <= 50) {
            sx = (int) (Math.random() * 100 + 50);
        }
        if (ay >= borderY) {
            sy = (int) -(Math.random() * 100 + 100);
        } else if (ay <= 30) {
            sy = (int) (Math.random() * 100 + 100);
        }
        if (sx != 0 || sy != 0) {
            thesprite.setSpeed(sx, sy);
        }
    }

    public void draw(Canvas canvas) {
        backSprite.draw(canvas);
        westWall.draw(canvas);
        asprite.draw(canvas);
        bsprite.draw(canvas);
        canvas.drawText(string, 20, 20, postext);
    }

    public void update(float dt) {

        moveto();
        //Task 2: show position:
        string = String.format("%2$10.1f %1$10.1f", asprite.getX(), asprite.getY());
        randomMove(bsprite);
        //        if (asprite.getX() >= 430) {
        //            //            System.out.println("crash east border!" + asprite.getSpeed());
        //            asprite.setSpeed(-asprite.getSpeed().getX(), asprite.getSpeed().getY());
        //        } else if (asprite.collides(westWall)) // collides is true first time, 
        // and change the object direction.
        //        {
        //            //            System.out.println("crash west border!");
        //            asprite.setSpeed(-asprite.getSpeed().getX(), asprite.getSpeed().getY());
        //        } else if (asprite.collides(
        //                bsprite)) // This collides is judged since the above collides. First execution collides 
        // will be
        //        // true at the first time without any judge.
        //        {
        //            //            System.out.println("crash each other!");
        //            asprite.setSpeed(-asprite.getSpeed().getX(), asprite.getSpeed().getY());
        //            //            bsprite.setScale(-1, 1);
        //            //            bsprite.setPosition(bsprite.getPosition().getX() + heliL.getWidth(),
        //            //                    bsprite.getPosition().getY());
        //            bsprite.setSpeed(-asprite.getSpeed().getX(), asprite.getSpeed().getY());
        //        }
        //
        //        if (bsprite.getX() >= 530) {
        //            //            System.out.println("crash east border!");
        //            bsprite.setSpeed(-bsprite.getSpeed().getX(), bsprite.getSpeed().getY());
        //        } else if (bsprite.collides(westWall)) // collides is true first time, 
        // and change the object direction.
        //        {
        //            //            System.out.println("crash west border!");
        //            bsprite.setSpeed(-bsprite.getSpeed().getX(), bsprite.getSpeed().getY());
        //        }

        direction(asprite);
        direction(bsprite);


        westWall.update(dt);
        asprite.update(dt);
        bsprite.update(dt);
    }

    /**
     * TASK 1: direction: move right or left
     *
     * @param sprite
     */
    private void direction(Sprite sprite) {
        if (sprite.getSpeed().getX() > 0) {
            sprite.setView(heliR);
        } else {
            sprite.setView(heliL);
        }
    }
}
