package com.example.frust;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * @author Jannis Gumz
 * Draws the game objects, interface, interlude and game over text on the screen.
 */
public class DrawingManager {

    private Paint positivePaint;
    private Paint negativePaint;
    private Paint neutralPaint;
    private int textSizeSmall;
    private int textSizeBig;
    private int screenWidth;
    private int screenHeight;

    /**
     * Creates a new DrawingManager. Initializes all used paints/colors (green for positive, red
     * for negative and white for neutral interactions) and text sizes.
     * @param screenWidth the width of the screen in pixel
     * @param screenHeight the height of the screen in pixel
     */
    public DrawingManager(int screenWidth, int screenHeight) {
        // text sizes will be set to a dynamic value based on screen width when drawing methods are
        // called, because the UI most likely is not fully loaded yet.
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        textSizeSmall = screenWidth/15;
        textSizeBig = screenWidth/10;

        neutralPaint = new Paint();
        neutralPaint.setColor(Color.WHITE);
        neutralPaint.setAntiAlias(true);
        neutralPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        neutralPaint.setTextSize(textSizeSmall);

        positivePaint = new Paint();
        positivePaint.setColor(Color.GREEN);
        positivePaint.setAntiAlias(true);
        positivePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        positivePaint.setTextSize(textSizeSmall);

        negativePaint = new Paint();
        negativePaint.setColor(Color.RED);
        negativePaint.setAntiAlias(true);
        negativePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        negativePaint.setTextSize(textSizeSmall);
    }

    /**
     * Draws the interface (score and level number) to the screen using white paint.
     * @param canvas represents the screen
     * @param score the current score
     * @param levelNumber the current level number
     */
    public void drawInterface(Canvas canvas, int score, int levelNumber) {
        neutralPaint.setTextAlign(Paint.Align.RIGHT);
        Rect bounds = new Rect();
        neutralPaint.getTextBounds(String.valueOf(score), 0,
                String.valueOf(score).length(), bounds);
        canvas.drawText(String.valueOf(score),screenWidth - 10,bounds.height()+10,
                neutralPaint);
        neutralPaint.setTextAlign(Paint.Align.LEFT);
        neutralPaint.getTextBounds(String.valueOf("Level " + levelNumber), 0,
                String.valueOf("Level " + levelNumber).length(), bounds);
        canvas.drawText(String.valueOf("Level " + levelNumber),10,bounds.height()+10,
                neutralPaint);
    }

    /**
     * Draws the green circular target on the screen
     * @param canvas represents the screen
     * @param target the FrustCircle representing the target to be touched
     */
    public void drawTarget(Canvas canvas, FrustCircle target) {
        canvas.drawCircle(target.getX(), target.getY(), target.getCurrentRadius(), positivePaint);
    }

    /**
     * Draws all red areas representing the enemies to the screen.
     * @param canvas represents the screen
     * @param enemies the FrustShapes represeting areas that must not be touched
     */
    public void drawEnemies(Canvas canvas, ArrayList<FrustShape> enemies) {
        for (FrustShape enemy : enemies) {
            if (enemy instanceof FrustCircle) {
                canvas.drawCircle(enemy.getX(), enemy.getY(),
                        ((FrustCircle) enemy).getCurrentRadius(), negativePaint);
            } else if (enemy instanceof FrustRectangle) {
                canvas.drawRect(enemy.getX(), enemy.getY(),
                        enemy.getX() + ((FrustRectangle)enemy).getWidth(),
                        enemy.getY() + ((FrustRectangle)enemy).getHeight(), negativePaint);
            }
        }
    }

    /**
     * Draw
     * @param canvas
     */
    public void drawGameOver(Canvas canvas) {
        // To Do
    }

    /**
     * Draws an interlude text on the screen. Consists of the level number and instructions for the
     * player how to beat the current level.
     * @param canvas represents the game screen
     */
    public void drawInterlude(Canvas canvas) {
        // To Do
    }
}
