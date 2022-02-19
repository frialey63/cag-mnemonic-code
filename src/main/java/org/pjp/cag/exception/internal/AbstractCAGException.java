package org.pjp.cag.exception.internal;

/**
 * The abstract exception containing common error code and address attributes, from which all other CAG exceptions are derived.
 * @author developer
 *
 */
public abstract class AbstractCAGException extends RuntimeException {

    private static final long serialVersionUID = -2604761222265709513L;

    /**
     * @param message The message
     */
    public AbstractCAGException(String message) {
        super(message);
    }

}
