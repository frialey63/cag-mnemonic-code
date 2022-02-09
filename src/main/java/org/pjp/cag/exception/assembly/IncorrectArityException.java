package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when an Order is encountered with incorrect arity, i.e. the number of arguments supplied is incorrect.
 * @author developer
 *
 */
public final class IncorrectArityException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 11;

    private static final long serialVersionUID = 4828019001253333230L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public IncorrectArityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public IncorrectArityException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
