package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represent the 4 possible directions of the lawnmower, using the NEWS coordinates.
 */
public enum LawnmowerOrientation {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    /**
     * Instruction associated to the orientation.
     */
    public final char instruction;

    /**
     * Create a new {@link LawnmowerOrientation} given an instruction.
     *
     * @param instruction instruction associated to the orientation
     */
    private LawnmowerOrientation(final char instruction) {
        this.instruction = instruction;
    }

    /**
     * Search for the {@link LawnmowerOrientation} associated to the give instruction.
     *
     * @param instruction the instruction to look for
     * @return maybe a {@link LawnmowerOrientation}
     */
    public static @NotNull Optional<LawnmowerOrientation> valueOfInstruction(final char instruction) {
        return Arrays.stream(values())
                .filter(e -> instruction == e.instruction)
                .findFirst();
    }
}
