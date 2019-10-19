package com.example.frust;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * @author Jannis Gumz
 * The screen the game will take place on. Upon initialization a GameThread is created, that
 * refreshes the position of all game objects (FrustShapes) every tick and draws them on this view.
 * The number of enemies (shapes that must be avoided), the position and behavior of enemies and
 * the target (a circle shape that should be targeted by the player) depends on the current Level.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private GameThread gameThread;
    private Context context;

    private FrustCircle target;
    private ArrayList<FrustShape> enemies;
    private Level currentLevel;
    private int score;
    private int levelNumber;
    private int goal;
    private int screenWidth;
    private int screenHeight;
    private SoundManager soundManager;

    private DrawingManager drawingManager;

    /**
     * Creates a new GameView, which represents the game screen, with its own GameThread that
     * refreshes the screen every tick (about 30 times/second).
     * Starts the game with a classic mode level.
     *
     * @param context the application context
     */
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.context = context;

        gameThread = new GameThread(getHolder(), this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        score = 0;
        levelNumber = 1;
        goal = 20;

        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        chooseNextLevel();
        drawingManager = new DrawingManager(screenWidth, screenHeight);

        target = currentLevel.getTarget();
        enemies = currentLevel.getEnemies();

        soundManager = SoundManager.getInstance(getContext());
        soundManager.playMusic();
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
     * Updates the positions of all enemies and the target, as well as the score and checks if the
     * levels goal is reached.
     */
    public void update() {
        currentLevel.onTick();
        target = currentLevel.getTarget();
        enemies = currentLevel.getEnemies();
        score = currentLevel.getScore();

        if (currentLevel.gameover) {
            soundManager.pauseMusic();
            gameThread.setRunning(false);
            Intent intent = new Intent(this.getContext(), GameOverActivity.class);
            intent.putExtra("score", String.valueOf(score));
            context.startActivity(intent);
        } else if (currentLevel.goalIsReached()) {
            levelNumber++;
            goal++;
            chooseNextLevel();
        }
    }

    /**
     * Chooses the next Level. There are three different types of levels: ClassicLevel,
     * StarsAndStripesLevel and SpeedTapLevel. A game starts with a ClassicLevel and loops through
     * the other levels every time a goal is reached.
     */
    private void chooseNextLevel() {
        int modulo = levelNumber % 3;
        switch (modulo) {
            case 1:
                currentLevel = new ClassicLevel(levelNumber, score, goal, screenWidth, screenHeight);
                break;
            case 2:
                currentLevel = new StarsAndStripesLevel(levelNumber, score, goal, screenWidth, screenHeight);
                break;
            case 0:
                currentLevel = new SpeedTapLevel(levelNumber, score, goal, screenWidth, screenHeight);
                break;
            default:
                currentLevel = new ClassicLevel(levelNumber, score, goal, screenWidth, screenHeight);
                break;
        }
    }

    /**
     * Draws the game elements and their current positions to the screen.
     * @param canvas the canvas used to draw images to the screen
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        target = currentLevel.getTarget();
        enemies = currentLevel.getEnemies();
        score = currentLevel.getScore();

        if (currentLevel.interludeIsRunning()) {
            drawingManager.drawInterlude(canvas, levelNumber, currentLevel.getInterludeText());
        } else {
            if (!currentLevel.isGameover()) {
                if (currentLevel instanceof ClassicLevel) {
                    drawingManager.drawEnemies(canvas, enemies);
                    drawingManager.drawTarget(canvas, target);
                } else if (currentLevel instanceof StarsAndStripesLevel) {
                    drawingManager.drawTarget(canvas, target);
                    drawingManager.drawEnemies(canvas, enemies);
                } else if (currentLevel instanceof SpeedTapLevel) {
                    drawingManager.drawTarget(canvas, target);
                }
                drawingManager.drawInterface(canvas, score, levelNumber);
            } else {
                drawingManager.drawGameOver(canvas, score);
            }
        }
    }

    /**
     * Registers touch events on the screen. Gets the coordinates of the touch events and checks for
     * collision with the target and enemies.
     *
     * @param v     the view
     * @param event the touch event
     * @return always false. Does not matter for this application
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (currentLevel.onTouch(x, y)) {
            soundManager.playPositiveSound();
        }
        return false;
    }
}
