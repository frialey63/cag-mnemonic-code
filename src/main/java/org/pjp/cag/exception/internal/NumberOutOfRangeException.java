package org.pjp.cag.exception.internal;

/**
 * This exception occurs if a word is mis-interpreted, e.g. it contains a number but it was interpreted as an Order.
 * @author developer
 *
 */
public final class NumberOutOfRangeException extends AbstractCAGException {

    private static final long serialVersionUID = 1781391522602004998L;

    /**
     * @param message The message
     */
    public NumberOutOfRangeException(String message) {
        super(message);
    }

}
