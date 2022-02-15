package org.pjp.cag.exception;

/**
 * This exception occurs if a number is read with too many decimal digits.
 * @author developer
 *
 */
public final class NumberReadException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 4;

    /**
     *
     */
    private static final long serialVersionUID = -6227058638981339204L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public NumberReadException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public NumberReadException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
