/**
 * Publicis Lawnmower Project
 * <p>
 * This test class allows us to check the App class (entry point of the Lawnmower application)
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(ReadProgramCommandParameterResolver.class)
@DisplayName("Testing ReadProgramCommand")
public class ReadProgramCommandTest {
    @Test
    void commandShouldBeCallable(ReadProgramCommand instance) {
        try {
            assertNotNull(instance.call(), "command should be callable");

            fail("command should raise an exception with no argument in command line");

        } catch (final Exception cause) {
            // with no argument in command line, this test should fail
        }
    }
}
