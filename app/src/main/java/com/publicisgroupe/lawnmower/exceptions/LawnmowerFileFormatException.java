package com.publicisgroupe.lawnmower.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * The class {@link LawnmowerFileFormatException} indicates that the given lawnmower file has a problem.
 * <p>
 * It could be a syntax problem (the format of the file is not valid) or semantic error (the file format is valid,
 * but does not make sense (two mowers starting at the same place for example)
 */
public class LawnmowerFileFormatException extends Exception {

    /**
     * Associated error code.
     */
    private int errcode;

    /**
     * Returns the error code associated to this exception.
     *
     * @return an <code>int</code> specific to this problem
     */
    public int getErrcode() {
        return errcode;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized; it can be initialized with {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param errcode error code associated to this specific problem
     */
    public LawnmowerFileFormatException(final int errcode,
                                        final @NotNull String message) {
        super(message);
        this.errcode = errcode;
    }
}
