package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

/**
 * This record represents a lawnmower to allow solving our problem.
 */
public class Lawnmower {

    private int x;
    private int y;
    private @NotNull LawnmowerOrientation orientation;
    private @NotNull LawnmowerInitRecord initRecord;

    /**
     * Define a new lawnmower with the given attributes.
     *
     * @param initRecord the {@link LawnmowerInitRecord} representing the initial position and instructions
     *                   of the lawmower
     */
    public Lawnmower(final @NotNull LawnmowerInitRecord initRecord) {
        this.initRecord = initRecord;
        this.x = initRecord.startX();
        this.y = initRecord.startY();
        this.orientation = initRecord.startOrientation();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public @NotNull LawnmowerOrientation getOrientation() {
        return orientation;
    }

    /**
     * Set the orientation of the Lawnmower.
     *
     * @param orientation new orientation to set
     */
    public void setOrientation(final @NotNull LawnmowerOrientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Change the orientation of the lawnmower, to the left.
     */
    public void turnLeft() {
        this.orientation = switch (orientation) {
            case NORTH -> LawnmowerOrientation.WEST;
            case WEST -> LawnmowerOrientation.SOUTH;
            case SOUTH -> LawnmowerOrientation.EAST;
            case EAST -> LawnmowerOrientation.NORTH;
        };
    }

    /**
     * Change the orientation of the lawnmower, to the right.
     */
    public void turnRight() {
        this.orientation = switch (orientation) {
            case NORTH -> LawnmowerOrientation.EAST;
            case WEST -> LawnmowerOrientation.NORTH;
            case SOUTH -> LawnmowerOrientation.WEST;
            case EAST -> LawnmowerOrientation.SOUTH;
        };
    }

    /**
     * Allows the lawnmower to move 1 step forward.
     */
    public void moveForward() {
        switch (orientation) {
            case NORTH -> y++;
            case EAST -> x++;
            case SOUTH -> y--;
            case WEST -> x--;
        }
    }
}
