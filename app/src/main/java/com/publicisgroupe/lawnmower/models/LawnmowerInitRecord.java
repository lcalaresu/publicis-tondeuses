package com.publicisgroupe.lawnmower.models;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Record to store the start value of a given Lawnmower.
 *
 * @param startX           the X coordinate of the lawnmower at the start
 * @param startY           the Y coordinate of the lawnmower at the start
 * @param startOrientation the orientation of the lawnmower at the start
 * @param instructions     all the instructions of the lawnmower
 */
public record LawnmowerInitRecord(int startX,
                                  int startY,
                                  @NotNull LawnmowerOrientation startOrientation,
                                  @NotNull List<Character> instructions) {
}
