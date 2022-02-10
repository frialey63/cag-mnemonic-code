package org.pjp.cag.exception;

/**
 * TODO maybe refactor exceptions into Command Pattern to reduce verbosity
 * The abstract exception containing common error code and address attributes, from which all other CAG exceptions are derived.
 * @author developer
 *
 */
public abstract class AbstractCAGException extends RuntimeException {

    private static final long serialVersionUID = -2604761222265709513L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public AbstractCAGException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public AbstractCAGException(String message) {
        super(message);
    }

    /**
     * @return The error code
     */
    public abstract int getErrorCode();

}
