package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Lawn.
 */
public record Lawn(int maxX, int maxY, List<Lawnmower> mowers) {
    public Lawn(final int maxX, final int maxY) {
        this(maxX, maxY, new ArrayList<>());
    }
}
