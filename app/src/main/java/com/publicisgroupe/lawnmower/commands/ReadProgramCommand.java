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

import com.publicisgroupe.lawnmower.services.I18n;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * This class represent the command to read the Lawnmower Program.
 */
@Command(
        name = "lawnmower", //$NON-NLS-1$
        version = "lawnmower 1.0", //$NON-NLS-1$
        mixinStandardHelpOptions = true,
        resourceBundle = I18n.MESSAGES_KEY,
        sortOptions = false
)
public class ReadProgramCommand implements Callable<Integer> {

    /**
     * the <code>--locale</code> option (to be ignored here, in the second phase of parsing).
     */
    @Option(names = {"-L", "--locale"}, //$NON-NLS-1$ //$NON-NLS-2$
            descriptionKey = "command.options.locale", //$NON-NLS-1$
            paramLabel = "LOCALE" //$NON-NLS-1$
    )
    private String ignored;

    /**
     * The <code>--file</code> option to choose the file to read.
     */
    @Option(names = {"-F", "--file"}, //$NON-NLS-1$  //$NON-NLS-2$
            required = true,
            paramLabel = "PROGRAM", //$NON-NLS-1$
            descriptionKey = "command.options.file" //$NON-NLS-1$
    )
    @NotNull
    private File lpfFile;

    /**
     * The <code>--help</code> option to display the usage message.
     */
    @Option(names = {"-H", "--help"}, //$NON-NLS-1$ //$NON-NLS-2$
            usageHelp = true,
            descriptionKey = "command.options.help" //$NON-NLS-1$
    )
    private boolean helpRequested = false;

    /**
     * The entry point of our command.
     *
     * @return <code>0</code> if everything went well, the <code>errorcode</code> otherwise.
     * @throws Exception           if any exception occured on runtime
     * @throws java.io.IOException if an I/O error occurs.
     */
    @Override
    public @NotNull Integer call() throws Exception {
        if (!lpfFile.exists() || !lpfFile.isFile() || !lpfFile.canRead()) {
            return 128; // errcode File not found
        }

        try (final @NotNull LineIterator it = FileUtils.lineIterator(lpfFile, "UTF-8")) {

            //
            if (it.hasNext()) {
                final @NotNull String firstLine = it.nextLine();
            } else {
                // format error : no "first line"
            }

            // iterate on each lawnmowers
            while (it.hasNext()) {
                final @NotNull String initPosition = it.nextLine();
                if (it.hasNext()) {
                    final @NotNull String instructions = it.nextLine();
                } else {
                    // error: lawnmower definer with init position, but no instructions
                }
            }
        }
        return 0;
    }
}


