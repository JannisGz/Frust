package com.example.frust;

/**
 * @author Jannis Gumz
 * A frust level mode. A small, green circle will appear at the top of the screen. It will move
 * towards the bottom of the screen. Once it reaches the bottom it will reappear at the top.
 * It has to be hit to increase score and progress. If it has been hit enough times (specified by
 * the parameter 'goal') the next level will start.
 * There are also red rectangles moving from side to side, which must be avoided. Touching them
 * results in a game over.
 */
public class StarsAndStripesLevel extends Level {

    private int speed;
    private int maxSpeed;

    /**
     * Creates a new  StarsAndStripesLevel, starting with the given score, the number of times the target
     * has to be hit (goal), the number of the level, which is used to calculate the score and
     * difficulty of the level. If invalid input is given the parameters will be set to default
     * values.
     * @param levelNumber the current level number (min 1)
     * @param score the current score (min 0)
     * @param goal the number of times the target has to be hit (min 0)
     * @param displayWidth the width of the screen (min 0)
     * @param displayHeight the height of the screen (min 0)
     */
    public StarsAndStripesLevel(int levelNumber, int score, int goal, int displayWidth, int displayHeight) {
        super(levelNumber, score, goal, displayWidth, displayHeight);

        target = new FrustCircle(displayWidth/2, displayHeight/16,
                displayWidth, displayHeight, displayHeight/16);

        for (int i = 0; i < 5; i++) {
            int randomX = random.nextInt(displayWidth);
            enemies.add(new FrustRectangle(randomX,displayHeight/16*(1+i*3 ),
                    displayWidth, displayHeight, displayWidth, displayHeight/8));
        }

        speed = 20 + levelNumber*2;
        maxSpeed = 50 + levelNumber*2;
        interludeText = "Tap the falling ball";
    }

    /**
     * Checks if a given point (x,y) collides with a target, enemy or nothing. If the target is hit
     * it will restart at the top and score and level progress will be increased.
     * The game will be set to game over, when an enemy is hit.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    @Override
    public boolean onTouch(int x, int y) {

        for (FrustShape enemy: enemies) {
            if (enemy.detectedCollision(x,y)) {
                gameover = true;
            }
        }

        if (target.detectedCollision(x,y)) {
            currentProgress += 2;
            score += (int) (levelNumber + 3*(1 - target.getY()/ (displayHeight*1.0)));
            target.setX(target.getCurrentRadius() +
                    random.nextInt(displayWidth - 2*target.getCurrentRadius()));
            target.setY(- target.getCurrentRadius() - displayHeight/10);

            if (speed < maxSpeed)
                speed += 2;
            return true;
        }
        return false;
    }

    /**
     * Updates the size of all enemies and the target circle every tick of the game loop. The
     * target will move from the top of the screen to the bottom and restart at the top. The
     * enemies (represented by red rectangles) will move from side to side.
     */
    @Override
    public void onTick() {
        if (interludeIsRunning()) {
            interludeTicks++;
        } else {
            target.setY(target.getY() + speed);

            if (target.getY() > displayHeight + target.getCurrentRadius()) {
                target.setY(-1 * target.getCurrentRadius());
            }

            int direction = 1;
            for (int i = 0; i < enemies.size(); i++) {
                if (i == 0 || i == 2 || i == 4) {
                    direction = -1;
                } else {
                    direction = 1;
                }

                enemies.get(i).setX(enemies.get(i).getX() - 3 * speed * direction);

                if (enemies.get(i).getX() < -1 * ((FrustRectangle)enemies.get(i)).getWidth() && direction > 0) {
                    enemies.get(i).setX(displayWidth + random.nextInt(displayWidth / 2));
                }

                if (enemies.get(i).getX() > displayWidth + ((FrustRectangle)enemies.get(i)).getWidth() && direction < 0) {
                    enemies.get(i).setX(0 - ((FrustRectangle)enemies.get(i)).getWidth() - random.nextInt(displayWidth / 2));
                }
            }
        }
    }
}
