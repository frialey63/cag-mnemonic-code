package org.pjp.cag.exception.internal;

/**
 * This exception occurs when an attempt is made to read/write a location in store with an invalid address.
 * @author developer
 *
 */
public final class IllegalLocationException extends AbstractCAGException {

    private static final long serialVersionUID = -5462381310870588141L;

    /**
     * @param message The message
     */
    public IllegalLocationException(String message) {
        super(message);
    }

}
