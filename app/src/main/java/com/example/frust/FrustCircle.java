package com.example.frust;

/**
 * @author Jannis Gumz
 * A circular shape for a frust level.
 * Is able to relocate to a random location within the specified bounds, change its radius,
 * position and detect collisions with other points.
 * There are to baseRadius-related parameters, that can be manipulated isolated from each other.
 * currentRadius represents the shrinking of the circle every game tick, while baseRadius
 * represents the radius without time related size changes.
 */
public class FrustCircle extends FrustShape {

    private int baseRadius;
    private int currentRadius;

    /**
     * Creates a new FrustCircle with the x and y arguments representing the mid point and the
     * baseRadius argument representing the baseRadius.
     * (maxX/maxY) represents the bottom right corner of the screen and has to be greater than (0/0)
     * @param x the x coordinate of the mid point
     * @param y the y coordinate of the mid point
     * @param maxX the maximum value of the x coordinate
     * @param maxY the maximum value of the y coordinate
     * @param radius has to be greater or equal to zero
     */
    public FrustCircle(int x, int y, int maxX, int maxY, int radius) {
        super(x, y, maxX, maxY);
        this.baseRadius = radius;
        if (radius < 0) {
            this.baseRadius = 0;
        }
        this.currentRadius = radius;
    }

    /**
     * Returns the original baseRadius of the circle
     * @return the original baseRadius of the circle
     */
    public int getBaseRadius() {
        return baseRadius;
    }

    /**
     * Sets the baseRadius to the specified value.
     * @param baseRadius the value the baseRadius will be set to. Has to be greater or equal to zero
     */
    public void setBaseRadius(int baseRadius) {
        if (baseRadius >= 0) {
            this.baseRadius = baseRadius;
        }
    }

    /**
     * Changes the baseRadius by the given integer. A positive value will make the radius grow and
     * a negative value will make it shrink. Resets to zero if it falls below zero.
     * @param amount the value the base radius gets changed by
     */
    public void changeBaseRadiusBy(int amount) {
        baseRadius += amount;
        if (baseRadius < 0) {
            baseRadius = 0;
        }
    }

    /**
     * Gets the current baseRadius.
     * @return the current baseRadius.
     */
    public int getCurrentRadius() {
        return currentRadius;
    }

    /**
     * Sets the current baseRadius to the desired value. Has to be greater or equal than zero.
     * @param currentRadius the desired currentRadius.
     */
    public void setCurrentRadius(int currentRadius) {
        if (currentRadius >= 0) {
            this.currentRadius = currentRadius;
        }
    }

    /**
     * Changes the currentRadius by the given integer. A positive value will make the radius grow and
     * a negative value will make it shrink. Resets to zero if it falls below zero.
     * @param amount the value the base radius gets changed by
     */
    public void changeCurrentRadiusBy(int amount) {
        currentRadius += amount;
        if (currentRadius < 0) {
            currentRadius = 0;
        }
    }

    /**
     * Checks if the point (xCoordinate/yCoordinate) is within bounds of the Circle.
     * @param xCoordinate x value of the point
     * @param yCoordinate y value of the point
     * @return true if the point is within bounds, else false
     */
    @Override
    public boolean detectedCollision(int xCoordinate, int yCoordinate) {
        return  xCoordinate >= x - currentRadius && xCoordinate <= x + currentRadius &&
                yCoordinate >= y - currentRadius && yCoordinate <= y + currentRadius;
    }

    /**
     * Changes the position of the circle to a random value within the top left corner (0/0) and
     * the bottom right corner (maxX/maxY) of the screen.
     */
    @Override
    public void relocatedWithinBounds() {
        x = baseRadius + random.nextInt(maxX - 2 * baseRadius);
        y = baseRadius + random.nextInt(maxY - 2 * baseRadius);
    }

    /**
     * Resets the current radius of the circle to the value stored in baseRadius.
     */
    public void resetCurrentRadius() {
        currentRadius = baseRadius;
    }

}
