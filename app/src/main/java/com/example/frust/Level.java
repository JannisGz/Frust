package com.example.frust;

import java.util.ArrayList;
import java.util.Random;

public abstract class Level {

    protected int levelNumber;
    protected int score;
    protected int goal;
    protected int currentProgress;
    protected boolean gameover;

    protected int displayHeight;
    protected int displayWidth;

    protected Random random;

    protected FrustCircle target;
    protected ArrayList<FrustShape> enemies;

    protected int interludeTicks = 0;
    private static int MAX_INTERLUDE_TICKS = 70;
    protected String interludeText;

    /**
     * Creates a new level with the given arguments. All parameters will be set to a
     * default value if invalid input is given.
     * @param levelNumber the current level number (min 1)
     * @param score the current score (min 0)
     * @param goal the number of times the target has to be hit (min 0)
     * @param displayWidth the width of the screen (min 0)
     * @param displayHeight the height of the screen (min 0)
     */
    public Level(int levelNumber, int score, int goal, int displayWidth, int displayHeight) {
        if (levelNumber < 1) {
            this.levelNumber = 1;
        } else {
            this.levelNumber = levelNumber;
        }
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
        if (goal < 0) {
            this.goal = 0;
        } else {
            this.goal = goal;
        }
        if (displayWidth < 0) {
            this.displayWidth = 0;
        } else {
            this.displayWidth = displayWidth;
        }
        if (displayHeight < 0) {
            this.displayHeight = 0;
        } else {
            this.displayHeight = displayHeight;
        }

        interludeText = "Tap Green, Avoid Red";

        currentProgress = 0;
        gameover = false;
        random = new Random();
        target = new FrustCircle(displayWidth / 2, displayHeight / 2,
                displayWidth, displayHeight, displayWidth / 4);
        enemies = new ArrayList<FrustShape>();
    }

    /**
     * Updates the position of the target and all enemies
     */
    public abstract void onTick();

    /**
     * Checks if the last touchscreen interaction hit the target, enemy or nothing.
     * @return true if the target was hit
     */
    public abstract boolean onTouch(int x, int y);

    /**
     * Returns whether the game is lost or still running
     * @return true if the game is lost else false
     */
    public boolean isGameover() {
        return gameover;
    }

    /**
     * Returns if the target was hit often enough to beat the goal of the level as specified in the
     * constructor
     * @return true if the current progess is equal or greater than the goal
     */
    public boolean goalIsReached() {
        return currentProgress >= goal;
    }

    /**
     * @return the FrustCircle representing the target
     */
    public FrustCircle getTarget() {
        return target;
    }

    /**
     * @return a list of FrustShapes representing the enemies
     */
    public ArrayList<FrustShape> getEnemies() {
        return enemies;
    }

    /**
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns if the level is in the interlude phase
     * @return true if interludeTicks is less than MAX_INTERLUDE_TICKS
     */
    public boolean interludeIsRunning() {
        return interludeTicks < MAX_INTERLUDE_TICKS;
    }

    /**
     * Returns the text that should be displayed between two levels
     * @return the interlude text
     */
    public String getInterludeText() {
        return interludeText;
    }
}
