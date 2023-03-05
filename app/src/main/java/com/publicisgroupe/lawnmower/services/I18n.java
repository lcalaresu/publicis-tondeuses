/**
 * Publicis Lawnmower Project
 * <p>
 * This class exists in order to support internationalisation in our application.
 * <p>
 * HISTORY:
 * - 05/03/2023 : Luc CALARESU : File creation
 */
package com.publicisgroupe.lawnmower.services;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class contains all the methods needed for the application's internationalisation.
 */
public class I18n {
    /**
     * Name of the application messages "properties" file.
     * <p>
     * Depending on the current locale, the user preferences, etc., the needed file will be chosen
     * (messages_fr, messages_en_US, etc.).
     */
    public final static @NotNull String MESSAGES_KEY = "com.publicisgroupe.lawnmower.messages";

    /**
     * Resource bundles containing the locale-specific objects.
     * <p>
     * This objects allows us to load our messages in the user's language.
     */
    private static ResourceBundle bundle;

    /**
     * Private constructor to forbid instantiation.
     */
    private I18n() {
    }

    /**
     * Return the locale currently used in the program, for this instance of the Java Virtual Machine.
     *
     * @return the current locale
     */
    public static final @NotNull Locale getLocale() {
        return Locale.getDefault();
    }

    /**
     * Return <code>true</code> if the given locale is supported, <code>false</code> otherwise
     * (for this instance of the Java Virtual Machine).
     *
     * @param loc the locale to check
     * @return <code>true</code> if the locale is supported and thus, can be used.
     */
    public static boolean isSupported(final @NotNull Locale loc) {
        Locale[] availableLocales = Locale.getAvailableLocales();
        return Arrays.asList(availableLocales).contains(loc);
    }

    /**
     * Change the current locale to the one specified (for this instance of the Java Virtual Machine).
     *
     * @param loc the new locale to use in the program
     * @throws SecurityException if a security manager exists and its checkPermission method doesn't allow the operation.
     */
    public static void setLocale(final @NotNull Locale loc) {
        Locale.setDefault(loc);
    }

    /**
     * Change the current locale to the one specified (for this instance of the Java Virtual Machine).
     *
     * @param loc the name of the new locale to use in the program
     * @throws SecurityException if a security manager exists and its checkPermission method doesn't allow the operation.
     */
    public static void setLocale(final @NotNull String loc) {
        final @NotNull Locale locale = new Locale(loc);
        if (isSupported(locale)) {
            setLocale(locale);
        }
    }

    /**
     * Return the message with the given key.
     *
     * @param key message key (ID) to look for. If the given message does NOT exist, this method return the `key` as
     *            a message.
     * @return the given message in the current locale
     * @throws java.util.MissingResourceException if no object for the given key can be found
     * @throws ClassCastException                 if the object found for the given key is not a string
     */
    public static @NotNull String getMessage(final @NotNull String key) {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(MESSAGES_KEY);
        }
        return bundle.getString(key);
    }

    /**
     * Return the message with the given key and arguments.
     *
     * @param key       message key (ID) to look for. If the given message does NOT exist, this method return the `key` as
     *                  a message.
     * @param arguments all the arguments needed for the message
     * @return the given message in the current locale
     * @throws java.util.MissingResourceException if no object for the given key can be found
     * @throws ClassCastException                 if the object found for the given key is not a string
     */
    public static @NotNull String getMessage(final @NotNull String key, final @NotNull Object... arguments) {
        final @NotNull MessageFormat formatter = new MessageFormat(getMessage(key), getLocale());
        return formatter.format(arguments);
    }
}
