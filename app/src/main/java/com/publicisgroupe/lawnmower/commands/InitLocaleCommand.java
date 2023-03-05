package com.publicisgroupe.lawnmower.commands;

import com.publicisgroupe.lawnmower.services.I18n;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine.Option;
import picocli.CommandLine.Unmatched;

import java.util.List;

/**
 * This class allows Picocli to use i18n.
 */
public class InitLocaleCommand {
    /**
     * The <code>--locale</code> option that will be checked on picocli first phase.
     *
     * @param locale the given option value
     */
    @Option(names = {"-L", "--locale"},
            description = "locale used for message texts")
    void setLocale(final @NotNull String locale) {
        I18n.setLocale(locale);
    }

    /*
     * Ignore any other parameters and options in the first parsing phase
     */
    @Unmatched
    List<String> remainder;
}
