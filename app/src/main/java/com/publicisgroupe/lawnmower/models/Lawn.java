package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This record represents a Lawn.
 */
public record Lawn(int maxX, int maxY, List<Lawnmower> mowers) {

    /**
     * Creates a new lawn with no mower.
     *
     * @param maxX maximum coordinate on the east direction
     * @param maxY maximum coordinate on the north direction
     */
    public Lawn(final int maxX, final int maxY) {
        this(maxX, maxY, new ArrayList<>());
    }

    /**
     * Checks if the mower is inside the lawn.
     *
     * @param mower the lawnmower we want to check
     * @return true if the mower is still on the lawn
     */
    public boolean contains(final @NotNull Lawnmower mower) {
        // the mower is inside if its coordinates are smaller than maxX and maxY.
        return (mower.getX() >= 0
                && mower.getY() >= 0
                && mower.getY() <= maxX
                && mower.getY() <= maxY);
    }

    /**
     * Move all the mower sequentially (one at a time)
     */
    public void moveAllMowers() {
        // for each mower
        for (Lawnmower mower : this.mowers) {
            // execute all of its instructions
            mower.executeInstructions(this.mowers);
            // display the mower current position and orientation
            System.out.println(mower);
        }
    }
}
