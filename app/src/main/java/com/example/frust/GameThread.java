package com.example.frust;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * @author Jannis Gumz
 * Manages the game loop
 */

public class GameThread extends Thread {
    private static int MAX_FPS = 30;
    private double avgFPS;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private static Canvas canvas;

    /**
     * Creates a thread for the currently active GameView. Every tic (at most 30 tics/second)
     * the thread will update the GameView and refresh the screen.
     * @param surfaceHolder the given SurfaceHolder
     * @param gameView the GameView that is to be bound to the thread
     */
    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    /**
     * Updates the GameView and draws it to the screen every tick. Tries to achieve a maximum of 30
     * ticks per second, resulting in 30 frames per second.
     * Logs the average frames per second to the console.
     */
    @Override
    public void run() {
        long startTime;
        long timeInMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/ MAX_FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeInMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeInMillis;

            try {
                this.sleep(waitTime);
            } catch (IllegalArgumentException e) {
                // To soon to redraw
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                avgFPS = 1000 / ((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgFPS);
            }
        }

    }

    /**
     * Sets the 'running' boolean for the game loop
     * @param isRunning the value the 'running' variable will be set to
     */
    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    /**
     * Returns the current state of the game loop, whether it is running or not
     * @return true if the game loop is currently running, else false
     */
    public boolean isRunning() {
        return running;
    }
}
