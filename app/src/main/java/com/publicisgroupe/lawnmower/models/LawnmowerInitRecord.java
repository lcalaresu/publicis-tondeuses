package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

/**
 * Record to store the start value of a given Lawnmower.
 *
 * @param startX           the X coordinate of the lawnmower at the start
 * @param startY           the Y coordinate of the lawnmower at the start
 * @param startOrientation the orientation of the lawnmower at the start
 */
public record LawnmowerInitRecord(int startX,
                                  int startY,
                                  @NotNull LawnmowerOrientation startOrientation) {
}
