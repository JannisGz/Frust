package com.example.frust;

/**
 * @author Jannis Gumz
 * A frust level mode. A green circle will appear in the center of the screen. Its size will slowly
 * increase. Touching the circle will make it increase faster and also increase the score.
 * The level runs for a limited time and will start the next level after.
 * There are no enemies in this level mode.
 */
public class SpeedTapLevel extends Level {

    private float ticks;

    /**
     * Creates a new  SpeedTapLevel, starting with the given score, the number of times the target
     * has to be hit (goal), the number of the level, which is used to calculate the score and
     * difficulty of the level. If invalid input is given the parameters will be set to default
     * values.
     * @param levelNumber the current level number (min 1)
     * @param score the current score (min 0)
     * @param goal the number of times the target has to be hit (min 0)
     * @param displayWidth the width of the screen (min 0)
     * @param displayHeight the height of the screen (min 0)
     */
    public SpeedTapLevel(int levelNumber, int score, int goal, int displayWidth, int displayHeight) {
        super(levelNumber, score, goal, displayWidth, displayHeight);

        target = new FrustCircle(displayWidth / 2, displayHeight / 2,
                displayWidth, displayHeight, displayWidth/4);

        ticks = 0;
        interludeText = "Tap as fast as possible";
    }

    /**
     * Checks if a given point (x,y) collides with a target or nothing. If the target is hit
     * the size of the circle and the score will be increased.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return true if the target was hit
     */
    @Override
    public boolean onTouch(int x, int y) {
        if (target.detectedCollision(x, y)) {
            target.changeCurrentRadiusBy(10);
            score += Math.max(1,Math.ceil(levelNumber/3));
            return true;
        }
        return false;
    }

    /**
     * Updates the size of the target if the interlude is not running.
     */
    @Override
    public void onTick() {
        if (interludeIsRunning()) {
            interludeTicks++;
        } else {
            ticks += 0.20;
            currentProgress = (int) ticks;
            target.changeCurrentRadiusBy(1);
        }
    }
}
