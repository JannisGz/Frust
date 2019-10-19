package com.example.frust;

public class SpeedTapLevel extends Level {

    private float ticks;

    public SpeedTapLevel(int levelNumber, int score, int goal, int displayWidth, int displayHeight) {
        super(levelNumber, score, goal, displayWidth, displayHeight);

        target = new FrustCircle(displayWidth / 2, displayHeight / 2,
                displayWidth, displayHeight, displayWidth/4);

        ticks = 0;
        interludeText = "Tap as fast as possible";
    }

    @Override
    public void onTouch(int x, int y) {
        if (target.detectedCollision(x, y)) {
            target.changeCurrentRadiusBy(10);
            score += Math.max(1,Math.ceil(levelNumber/3));
        }
    }

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
