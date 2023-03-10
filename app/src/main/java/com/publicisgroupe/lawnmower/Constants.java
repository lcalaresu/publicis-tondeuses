package com.publicisgroupe.lawnmower;

import com.publicisgroupe.lawnmower.services.I18n;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains all the constants of the Lawnmower project.
 */
public class Constants {
    private Constants() {
        throw new RuntimeException(
                I18n.getMessage("private.class.instantiation") //$NON-NLS-1$
        );
    }

    public static final @NotNull @NonNls String SPACE = " "; //$NON-NLS-1$
    public static final @NotNull @NonNls String CHARSET_UTF8 = "UTF-8"; //$NON-NLS-1$

    public static final class Retcode {
        public static final int OK = 0;
        public static final int READCOMMAND_EMPTYFILE = 129;
        public static final int READCOMMAND_MISSING_ILE = 128;
        public static final int READCOMMAND_WRONG_FILE = 127;
        public static final int READCOMMAND_IOERROR_FILE = 126;
        public static final int READCOMMAND_FORMAT_ERROR = 125;
        public static final int READCOMMAND_MOWER_MISSING_LINE = 124;
        public static final int READCOMMAND_MOWER_INVALID_INSTRUCTIONS = 123;

    }
}
