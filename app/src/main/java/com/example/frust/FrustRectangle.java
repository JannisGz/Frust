package com.example.frust;

public class FrustRectangle extends FrustShape {

    private int width;
    private int height;

    /**
     * Creates a new FrustRectangle with the 'x' and 'y' arguments representing the upper left
     * corner of the rectangle and 'width' and 'height' the dimensions.
     * (maxX/maxY) represents the bottom right corner of the screen and has to be greater than (0/0)
     * @param x the x coordinate of the upper left corner
     * @param y the y coordinate of the upper left corner
     * @param maxX the maximum for the x coordinate
     * @param maxY the maximum for the y coordinate
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public FrustRectangle(int x, int y, int maxX, int maxY, int width, int height) {
        super(x, y, maxX, maxY);
        if (width < 0) {
            width = 0;
        }
        if (height < 0) {
            height = 0;
        }
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle to the desired value.
     * @param width new width of the rectangle. Has to be greater than zero.
     */
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }

    /**
     * @return the height of the rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle to the desired value.
     * @param width new height of the rectangle. Has to be greater than zero.
     */
    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
    }

    /**
     * Checks if a given point (xCoordinate, yCoordinate) is within bounds of the rectangle.
     * @param xCoordinate x coordinate of the point
     * @param yCoordinate y coordinate of the point
     * @return
     */
    @Override
    public boolean detectedCollision(int xCoordinate, int yCoordinate) {
        return xCoordinate >= x && xCoordinate <= x + width
                && yCoordinate >= y && yCoordinate <= y + height;
    }

    /**
     * Changes the position of the rectangle to a random value within the top left corner (0/0) and
     * the bottom right corner (maxX/maxY) of the screen.
     */
    @Override
    public void relocatedWithinBounds() {
        x = random.nextInt(maxX - width);
        y = random.nextInt(maxY - height);
    }
}
