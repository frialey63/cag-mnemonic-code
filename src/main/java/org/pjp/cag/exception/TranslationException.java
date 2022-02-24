package org.pjp.cag.exception;

/**
 * This exception wraps the errors which may occur during the translation of a program.
 * @author developer
 *
 */
public class TranslationException extends Exception {

    private static final long serialVersionUID = -4242386450213011282L;

    private final TranslationError error;

    public TranslationException(TranslationError error) {
        super();
        this.error = error;
    }

    public TranslationError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return String.format("ERR %2d %%4d %s", error.number(), error.description());
    }

}
