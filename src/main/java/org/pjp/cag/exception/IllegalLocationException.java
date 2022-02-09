package org.pjp.cag.exception;

/**
 * This exception occurs when an attempt is made to read/write a location in store with an invalid address.
 * @author developer
 *
 */
public final class IllegalLocationException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 2;

    private static final long serialVersionUID = -5462381310870588141L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public IllegalLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public IllegalLocationException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
