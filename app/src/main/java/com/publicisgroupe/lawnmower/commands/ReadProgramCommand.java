/**
 * Publicis Lawnmower Project
 * <p>
 * This class allows the user to read the 'lawnmower programming file (*.lpf)' to be read, in order to extract its
 * content.
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower.commands;

import org.jetbrains.annotations.NotNull;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
        name = "lawnmower",
        description = "The Lawnmower application allows you to read a 'lawnmower programming file (*.lpf)' " +
                "in order to program your lawnmowers."
)
public class ReadProgramCommand implements Callable<Integer> {

    @Option(names = {"-F", "--file"},
            required = true,
            paramLabel = "PROGRAM",
            description = "The LPF file that contains the lawnmower program.")
    @NotNull
    private File lpfFile;

    @Option(names = {"-H", "--help"},
            usageHelp = true,
            description = "Display a help message")
    private boolean helpRequested = false;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}