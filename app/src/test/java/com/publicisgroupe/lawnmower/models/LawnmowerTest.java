/**
 * Publicis Lawnmower Project
 * <p>
 * This test class allows us to check the App class (entry point of the Lawnmower application)
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower.models;

import com.publicisgroupe.lawnmower.commands.ReadProgramCommand;
import com.publicisgroupe.lawnmower.commands.ReadProgramCommandParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Lawnmower")
public class LawnmowerTest {
    private Lawnmower getDefaultMower(List<Character> instructions) {
        LawnmowerOrientation initOrientation = LawnmowerOrientation.NORTH;
        LawnmowerInitRecord init = new LawnmowerInitRecord(0, 0, initOrientation);

        // new lawnmower without instruction
        return new Lawnmower(init, instructions);
    }

    @Test
    void shouldNotMoveIfNoInstruction() {
        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of());

        // execute all instructions (none here)
        mower.executeInstructions(List.of(mower));

        // nothing should have changed
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 0);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.NORTH);
    }


    @Test
    void shouldTurnRightIfSaySo() {
        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of('D'));

        // execute all instructions (none here)
        mower.executeInstructions(List.of(mower));

        // nothing should have changed
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 0);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.EAST);
    }

    @Test
    void shouldTurnLeftIfSaySo() {
        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of('G'));

        // execute all instructions (none here)
        mower.executeInstructions(List.of(mower));

        // nothing should have changed
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 0);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.WEST);
    }

    @Test
    void shouldMoveForwardIfSaySo() {
        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of('A'));

        // execute all instructions (none here)
        mower.executeInstructions(List.of(mower));

        // nothing should have changed
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 1);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.NORTH);
    }

    @Test
    void shouldFollowListOfInstructions() {
        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of('D', 'A'));

        // execute all instructions (none here)
        mower.executeInstructions(List.of(mower));

        // nothing should have changed
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 0);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.EAST);
    }

    @Test
    void shouldNotMoveIfAnotherMowerBlocking() {
        LawnmowerOrientation initOrientation = LawnmowerOrientation.NORTH;
        LawnmowerInitRecord init = new LawnmowerInitRecord(1, 0, initOrientation);

        // new lawnmower without instruction
        Lawnmower blockingMower = new Lawnmower(init, List.of());

        // new lawnmower without instruction
        Lawnmower mower = getDefaultMower(List.of('D', 'A', 'D'));

        // execute all instructions (none here)
        mower.executeInstructions(List.of(blockingMower, mower));

        // nothing should have changed
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 0);
        assertEquals(mower.getOrientation(), LawnmowerOrientation.SOUTH);
    }
}
