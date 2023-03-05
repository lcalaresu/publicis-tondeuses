/**
 * Publicis Lawnmower Project
 * <p>
 * This class is the entry point of the Lawnmower application.
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower;

import com.publicisgroupe.lawnmower.commands.InitLocaleCommand;
import com.publicisgroupe.lawnmower.commands.ReadProgramCommand;
import com.publicisgroupe.lawnmower.services.I18n;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;

/**
 * Entry point of the Lawnmower application.
 */
public class App {

    public static void main(final @NotNull String[] args) {
        // first phase: configure locale
        new CommandLine(new InitLocaleCommand()).parseArgs(args);

        // second phase: parse all args (ignoring --locale) and run the app
        // ReadProgramCommand implements Callable, so parsing, error handling and handling user
        // requests for usage help or version help can be done with one line of code with picocli.
        int exitCode = new CommandLine(new ReadProgramCommand()).execute(args);
        if (exitCode != 0) {
            System.err.println(
                    I18n.getMessage("main.error.with.code", exitCode)
            );
        }
        // Sends the error code to the commandline
        System.exit(exitCode);
    }
}
