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

import com.publicisgroupe.lawnmower.Constants;
import com.publicisgroupe.lawnmower.exceptions.LawnmowerFileFormatException;
import com.publicisgroupe.lawnmower.models.Lawn;
import com.publicisgroupe.lawnmower.models.Lawnmower;
import com.publicisgroupe.lawnmower.models.LawnmowerInitRecord;
import com.publicisgroupe.lawnmower.models.LawnmowerOrientation;
import com.publicisgroupe.lawnmower.services.I18n;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

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
        // make sure the given file is valid
        isFileValid();

        // starting here, we have a nice file
        Lawn lawn;
        try (final @NotNull LineIterator it = FileUtils.lineIterator(lpfFile, Constants.CHARSET_UTF8)) {
            // iterate on each line of the file
            lawn = iterateOnFileLines(it);
        }

        lawn.moveAllMowers();

        // errcode OK = 0 (the other retcodes are contained in the exception)
        return Constants.Retcode.OK;
    }

    /**
     * Iterate on all the lines of the given file (using its iterator)
     *
     * @param it current iterator
     * @return
     * @throws LawnmowerFileFormatException in one of those cases :
     *                                      <ul>
     *                                          <li>the file is empty</li>
     *                                          <li>the file format is not valid</li>
     *                                      </ul>
     */
    private @NotNull Lawn iterateOnFileLines(final @NotNull LineIterator it)
            throws LawnmowerFileFormatException {

        // if the file does not have AT LEAST 1 line, we cannot continue
        if (!it.hasNext()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_EMPTYFILE,
                    I18n.getMessage("readcommand.empty.file.error", lpfFile.getName())); //$NON-NLS-1$
        }

        // extract the first line of the file
        final @NotNull Lawn lawn = extractFirstLine(it);

        // iterate on each lawnmower (1 lawnmower = 2 lines)
        while (it.hasNext()) {
            final Lawnmower nextMower = extractNextMower(it);
            if (lawn.contains(nextMower)) {
                // add the mower to the lawn only if it is not out of bounds
                lawn.mowers().add(nextMower);
            } else {
                // if out of bounds, we display a message but continue (without the lawnmower)
                System.err.println(
                        I18n.getMessage("readcommand.mower.out.of.bounds", lawn.mowers().size()) //$NON-NLS-1$
                );
            }
        }
        return lawn;
    }

    /**
     * Extract the next lawnmower in the file.
     *
     * @param it current file iterator
     * @return the associated {@link Lawnmower} instance
     * @throws LawnmowerFileFormatException if the file format is not valid
     */
    private static @NotNull Lawnmower extractNextMower(final @NotNull LineIterator it)
            throws LawnmowerFileFormatException {

        // extract the mower 's init position
        if (!it.hasNext()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_MOWER_MISSING_LINE,
                    I18n.getMessage("readcommand.mower.missing.line.1")); //$NON-NLS-1$
        }
        final LawnmowerInitRecord initPosition = extractMowerInitValues(it);

        // extract the mower 's instructions
        if (!it.hasNext()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_MOWER_MISSING_LINE,
                    I18n.getMessage("readcommand.mower.missing.line.2")); //$NON-NLS-1$
        }
        final List<Character> instructionList = extractMowerInstructions(it);

        // returns the Lawnmower
        return new Lawnmower(initPosition, instructionList);
    }

    /**
     * Extract the instructions of the lawnmower.
     *
     * @param it current file iterator
     * @return a {@link List} of {@link Character}s, each one of them being an instruction
     * @throws LawnmowerFileFormatException if the file format is not valid
     */
    @NotNull
    private static List<Character> extractMowerInstructions(final @NotNull LineIterator it)
            throws LawnmowerFileFormatException {
        // extract the instructions as a list of characters
        final String instructionString = it.nextLine();
        // make sur its content is valid
        if (!instructionString.matches("[DGA]*")) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_MOWER_INVALID_INSTRUCTIONS,
                    I18n.getMessage("readcommand.mower.invalid.instructions", instructionString)); //$NON-NLS-1$
        }
        // convert the string into a list of characters
        return instructionString
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    /**
     * Extract the init position of the lawnmower.
     *
     * @param it current file iterator
     * @return the current {@link LawnmowerInitRecord}
     * @throws LawnmowerFileFormatException if the file format is not valid
     */
    private static @NotNull LawnmowerInitRecord extractMowerInitValues(final @NotNull LineIterator it)
            throws LawnmowerFileFormatException {

        // get the firstline, and split its tokens
        final @NotNull String firstLine = it.nextLine();
        final @NotNull String[] tokens = firstLine.split(Constants.SPACE);

        // we expect 3 elements. No more, no less
        if (tokens.length != 3) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_FORMAT_ERROR,
                    I18n.getMessage("readcommand.format.error.firstline.args")); //$NON-NLS-1$
        }

        // Define the 3 starting elements of the mower
        int x;
        int y;
        LawnmowerOrientation orientation;

        // extract the first 2 elements (x and y)
        try {
            x = Integer.parseInt(tokens[0]);
            y = Integer.parseInt(tokens[1]);

        } catch (final @NotNull NumberFormatException nfe) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_FORMAT_ERROR,
                    I18n.getMessage("readcommand.format.error.firstline.int")); //$NON-NLS-1$
        }

        // extract the 3 element (orientation)
        try {
            orientation = LawnmowerOrientation
                    .valueOfInstruction(tokens[2].charAt(0))
                    .orElseThrow();

        } catch (final @NotNull IllegalArgumentException | IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_FORMAT_ERROR,
                    I18n.getMessage("readcommand.format.error.firstline.orientation")); //$NON-NLS-1$
        }

        // return the init record
        return new LawnmowerInitRecord(x, y, orientation);
    }

    /**
     * Extract the first line of the file.
     *
     * @param it current file iterator
     * @return the {@link Lawn} of the top right lawn point
     * @throws LawnmowerFileFormatException if the file format is not valid
     */
    private static @NotNull Lawn extractFirstLine(final @NotNull LineIterator it)
            throws LawnmowerFileFormatException {

        // get the first line, and split its tokens
        final @NotNull String firstLine = it.nextLine();
        final @NotNull String[] tokens = firstLine.split(Constants.SPACE);

        // we expect 2 elements. No more, no less
        if (tokens.length != 2) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_FORMAT_ERROR,
                    I18n.getMessage("readcommand.format.error.firstline.args")); //$NON-NLS-1$
        }

        // Define the coordinates of the lawn
        int x;
        int y;

        // extract the first 2 elements (x and y)
        try {
            x = Integer.parseInt(tokens[0]);
            y = Integer.parseInt(tokens[1]);

        } catch (final @NotNull NumberFormatException nfe) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_FORMAT_ERROR,
                    I18n.getMessage("readcommand.format.error.firstline.int")); //$NON-NLS-1$
        }

        // return the init record
        return new Lawn(x, y);
    }

    /**
     * This method make sure the file we are supposed to check is valid.
     *
     * @throws LawnmowerFileFormatException in one of those cases :
     *                                      <ul>
     *                                          <li>the file does not exist</li>
     *                                          <li>the file is NOT a file (a folder for example)</li>
     *                                          <li>the file cannot be read</li>
     *                                      </ul>
     */
    private void isFileValid() throws LawnmowerFileFormatException {
        final @NotNull String filename = lpfFile.getName();


        if (!lpfFile.exists()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_MISSING_ILE,
                    I18n.getMessage("readcommand.file.missing", lpfFile.getAbsolutePath())); //$NON-NLS-1$

        } else if (!lpfFile.isFile()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_WRONG_FILE,
                    I18n.getMessage("readcommand.file.notfile", filename)); //$NON-NLS-1$

        } else if (!lpfFile.canRead()) {
            throw new LawnmowerFileFormatException(
                    Constants.Retcode.READCOMMAND_IOERROR_FILE,
                    I18n.getMessage("readcommand.file.ioerror", filename)); //$NON-NLS-1$
        }
    }
}


