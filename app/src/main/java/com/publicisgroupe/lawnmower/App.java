/**
 * Publicis Lawnmower Project
 * <p>
 * This class is the entry point of the Lawnmower application.
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower;

import com.publicisgroupe.lawnmower.commands.ReadProgramCommand;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;

/**
 * Entry point of the Lawnmower application.
 */
public class App {

    public static void main(final @NotNull String[] args) {
        // ReadProgramCommand implements Callable, so parsing, error handling and handling user
        // requests for usage help or version help can be done with one line of code with picocli.
        int exitCode = new CommandLine(new ReadProgramCommand()).execute(args);
        System.exit(exitCode);
    }
}
