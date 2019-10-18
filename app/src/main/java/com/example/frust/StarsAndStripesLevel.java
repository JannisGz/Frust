package com.example.frust;

public class StarsAndStripesLevel extends Level {

    private int speed;
    private int maxSpeed;

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

    @Override
    public void onTouch(int x, int y) {

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
        }
    }

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
