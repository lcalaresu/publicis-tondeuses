/**
 * Publicis Lawnmower Project
 * <p>
 * This test class allows us to check the App class (entry point of the Lawnmower application)
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ReadProgramCommandTest {
    @Test
    void commandShouldBeCallable() {
        final ReadProgramCommand classUnderTest = new ReadProgramCommand();
        try {
            assertNotNull(classUnderTest.call(), "command should be callable");
        } catch (final Exception cause) {
            fail("command should not raise an exception", cause);
        }
    }
}
