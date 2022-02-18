package org.pjp.cag.error;

/**
 * This exception wraps the errors which may occur during the translation of a program.
 * @author developer
 *
 */
public class TranslationException extends Exception {

    private static final long serialVersionUID = -4242386450213011282L;

    public TranslationException(TranslationError error) {
        super(error.toString());
    }


}