package org.pjp.cag.exception;

/**
 * This exception occurs if a word is mis-interpreted, e.g. it contains a number but it was interpreted as an Order.
 * @author developer
 *
 */
public final class FaultyWordException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 1;

    private static final long serialVersionUID = -4001170531420888949L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public FaultyWordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public FaultyWordException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
