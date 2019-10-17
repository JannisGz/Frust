package com.example.frust;

import java.util.Random;

/**
 * @author Jannis Gumz
 * The basic shape concept to be used in a Frust Level. X and Y coordinates represent the postion
 * of the shape, while maxX and maxY coordinates should be used to identify the bottom right corner
 * of the screen you want to use them on.
 * The position of the shape can be changed both randomly or to a desired value using the setter
 * methods.
 */
public abstract class FrustShape {

    protected int x;
    protected int y;
    protected int maxX;
    protected int maxY;
    protected Random random;

    /**
     * Creates a new FrustShape, located at the given x,y coodinates.
     * (x/y) = (0/0) represents the upper left corner of the screen and (maxX/maxY) should represent
     * the bottom right corner of the screen. Has to be greater than (0/0).
     * @param x the x coordinate of the initial position
     * @param y the y coordinate of the initial position
     * @param maxX the maximum value of the x coordinate
     * @param maxY the maximum value of the y coordinate
     */
    public FrustShape(int x, int y, int maxX, int maxY) {
        if (maxX < 0) {
            maxX = 0;
        }
        if (maxY < 0) {
            maxY = 0;
        }
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.random = new Random();
    }

    /**
     * Returns the x value of the current position.
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate of the current position to the given value
     * @param x the desired x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y value of the current position.
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate of the current position to the given value
     * @param y the desired y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Detects if the shape contains the given point
     * @param xCoordinate x value of the point
     * @param yCoordinate y value of the point
     * @return true if the point is within bounds
     */
    public abstract boolean detectedCollision(int xCoordinate, int yCoordinate);

    /**
     * Moves the position by the given offsets
     * @param offsetX offset for the x coordinate.
     * @param offsetY offset for the y coordinate.
     */
    public void moveBy(int offsetX, int offsetY) {
        x += offsetX;
        y += offsetY;
    }

    /**
     * Sets x and y coordinates to a random value in the range 0 to the maximum values defined
     * in the constructor.
     */
    public abstract void relocatedWithinBounds();
}
