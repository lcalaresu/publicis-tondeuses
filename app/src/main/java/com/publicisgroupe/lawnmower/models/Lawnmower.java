package com.publicisgroupe.lawnmower.models;

import com.publicisgroupe.lawnmower.services.I18n;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This class represents a lawnmower to allow solving our problem.
 */
public class Lawnmower {

    /**
     * Current X position of the lawn mower.
     */
    private int x;

    /**
     * Current Y position of the lawn mower.
     */
    private int y;

    /**
     * Current orientation of the mower.
     */
    private @NotNull LawnmowerOrientation orientation;

    /**
     * The initial position / orientation of the lawnmower.
     */
    private final @NotNull LawnmowerInitRecord initRecord;

    /**
     * All the lawnmower instructions.
     */
    private final @NotNull List<Character> instructions;

    /**
     * Define a new lawnmower with the given attributes.
     *
     * @param initRecord   the {@link LawnmowerInitRecord} representing the initial position of the lawmower
     * @param instructions all the instructions of the lawmower
     */
    public Lawnmower(
            final @NotNull LawnmowerInitRecord initRecord,
            final @NotNull List<Character> instructions) {
        // init position and direction, and instructions should never change
        this.initRecord = initRecord;
        this.instructions = instructions;

        // set the start position and direction
        this.x = initRecord.startX();
        this.y = initRecord.startY();
        this.orientation = initRecord.startOrientation();
    }

    /**
     * Return the X coordinate of the lawnmower.
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Update the X coordinate of the lawnmower.
     *
     * @param x new X value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return the Y coordinate of the lawnmower.
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Update the Y coordiniate of the lawnmower.
     *
     * @param y new Y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Orientation of the lawnmower.
     *
     * @return LawnmowerOrientation
     */
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

    /**
     * Allows the lawnmower to move 1 step forward.
     */
    public void moveBackward() {
        switch (orientation) {
            case NORTH -> y--;
            case EAST -> x--;
            case SOUTH -> y++;
            case WEST -> x++;
        }
    }

    /**
     * Move forward if it does not imply a collision.
     *
     * @param mowers list of all the other mowers (might include this one) in order to detect collisions
     */
    public void moveForwardIfPossible(final @NotNull List<Lawnmower> mowers) {
        // try to move the mower forward
        this.moveForward();
        // and check for collision
        if (this.collideWithAny(mowers)) {
            // if any collision, we go back to the previous coordinates : the mower will not move
            this.moveBackward();
            System.err.println(
                    I18n.getMessage("mower.cannot.move") //$NON-NLS-1$
            );
        }
    }

    /**
     * Check if the current mower collides with any other mower.
     *
     * @param mowers list of all the other mowers (might include this one) in order to detect collisions
     * @return <code>true</code> if a collision is detected
     */
    private boolean collideWithAny(final @NotNull List<Lawnmower> mowers) {
        // return true if any mower (not the current one) has the same coordinates
        return mowers.stream().anyMatch(mower -> mower != this && mower.getX() == x && mower.getY() == y);
    }

    /**
     * Execute all the instructions of the lawn mower.
     * <p>
     * If a collision is detected, the current instruction is ignored, and the next ont started
     *
     * @param otherMowers list of all the other mowers (might include this one) in order to detect collisions
     */
    public void executeInstructions(final @NotNull List<Lawnmower> otherMowers) {
        // check all instructions
        for (Character instruction : instructions) {
            // call the right method depending on the instruction
            switch (instruction) {
                case 'D' -> turnRight();
                case 'G' -> turnLeft();
                case 'A' -> moveForwardIfPossible(otherMowers);
            }
        }
    }

    /**
     * Returns the String representation of the Lawnmower.
     *
     * @return string content of the lawnmower position and orientation
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", x, y, orientation.instruction);
    }
}
