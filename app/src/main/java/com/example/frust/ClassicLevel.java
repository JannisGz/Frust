package com.example.frust;

/**
 * @author Jannis Gumz
 * The classic frust level. A green circle will appear on the screen. It will slowly decrease in
 * size. If it disappears the game is lost. If the user hits the target before it disappears, it
 * will relocate to a different position on the screen, repeating until it has been hit enough times
 * (specified in the goal parameter).
 * There are also red circles spawning, which must be avoided. Touching them results in a
 * game over.
 */
public class ClassicLevel extends Level {

    private int enemyCount;
    private int maxEnemies;

    private int minSpeed;
    private int maxSpeed;
    private float currentSpeed;
    private float speedIncrease;

    /**
     * Creates a new Classic Level, starting with the given score, the number of times the target
     * has to be hit (goal), the number of the level, which is used to calculate the score and
     * difficulty of the level. If invalid input is given the parameters will be set to default
     * values.
     * @param levelNumber the current level number (min 1)
     * @param score the current score (min 0)
     * @param goal the number of times the target has to be hit (min 0)
     * @param displayWidth the width of the screen (min 0)
     * @param displayHeight the height of the screen (min 0)
     */
    public ClassicLevel(int levelNumber, int score, int goal, int displayWidth, int displayHeight) {
        super(levelNumber, score, goal, displayWidth, displayHeight);

        enemyCount = 0;
        maxEnemies = Math.max(5, levelNumber * 2);
        minSpeed = Math.max(3, (int) (levelNumber * 1.5));
        maxSpeed = Math.max(8,  levelNumber * 2);
        currentSpeed = minSpeed;
        speedIncrease = 0.1f * (int) (levelNumber / 4 + 1);

    }

    /**
     * Checks if a given point (x,y) collides with a target, enemy or nothing. If the target is hit
     * all circles will change their position randomly, the score, difficulty and current progress
     * towards the goal will be increased.
     * The game will be set to game over, when an enemy is hit.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return true if the target was hit
     */
    @Override
    public boolean onTouch(int x, int y) {

        for (FrustShape enemy : enemies) {
            if (enemy.detectedCollision(x, y) && !target.detectedCollision(x, y)) {
                gameover = true;
            }
        }

        if (!gameover && target.detectedCollision(x, y)) {
            currentProgress += 1;
            target.relocatedWithinBounds();
            score += (int) levelNumber + 5 * (target.getCurrentRadius() * target.getCurrentRadius() /
                    (target.getBaseRadius() * 1.0 * target.getBaseRadius()));
            target.changeBaseRadiusBy(-3);
            target.resetCurrentRadius();

            if (enemyCount < maxEnemies) {
                enemies.add(new FrustCircle(displayWidth / 2, displayHeight / 2,
                        displayWidth, displayHeight, displayWidth / 5));
                enemyCount += 1;
            }

            for (FrustShape enemy : enemies) {
                enemy.relocatedWithinBounds();
                ((FrustCircle) enemy).changeBaseRadiusBy(3);
                ((FrustCircle) enemy).resetCurrentRadius();
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed += speedIncrease;
            }
            return true;
        }
        return false;
    }

    /**
     * Updates the size of all enemies and the target circle every tick of the game loop. The
     * target will shrink and enemy circles will increase in size.
     * Also sets game over to true if the target is no longer visible.
     */
    @Override
    public void onTick() {
        if (interludeIsRunning()) {
            interludeTicks++;
        } else {
            if (target.getCurrentRadius() <= 0) {
                gameover = true;
            } else {
                target.changeCurrentRadiusBy(-(int) currentSpeed);
                for (FrustShape enemy : enemies) {
                    ((FrustCircle) enemy).changeCurrentRadiusBy((int) currentSpeed);
                }
            }
        }
    }
}
