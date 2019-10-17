package com.example.frust;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * @author Jannis Gumz
 * The screen the game will take place on. Upon initialization a GameThread is created, that
 * refreshes the position of all game objects (FrustShapes) every tick and draws them on this view.
 * The number of enemies (shapes that must be avoided), the position and behavior of enemies and
 * the target (a circle shape that should be targeted by the player) depends on the current Level.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    private FrustCircle target;
    private ArrayList<FrustShape> enemies;
    private Level currentLevel;
    private int score;

    /**
     * Creates a new GameView with the given context. Creates a new GameThread that refreshes the
     * GameView every tick (about 30 times/second)
     * Starts the game with a avoidance mode level.
     * @param context the given context
     */
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);
        setFocusable(true);

        score = 0;
        currentLevel = new ClassicLevel(1,0, 20, getWidth(), getHeight());
        target = currentLevel.getTarget();
        enemies = currentLevel.getEnemies();



    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameThread = new GameThread(getHolder(), this);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.setRunning(false);
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Updates the positions of all enemies and the target
     */
    public void update() {
        currentLevel.onTick();
        target = currentLevel.getTarget();
        enemies = currentLevel.getEnemies();
    }

    /**
     * Draws the game elements and their current positions to the screen.
     * @param canvas the canvas used to draw images to the screen
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!currentLevel.isGameover()) {
            
        }
        //draw enemies
        //draw target
        //draw score
    }
}
