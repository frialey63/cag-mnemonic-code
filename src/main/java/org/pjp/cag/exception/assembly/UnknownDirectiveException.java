package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when an unknown directive is encountered.
 * @author developer
 *
 */
public final class UnknownDirectiveException extends AbstractCAGException {
    /**
     * The error code.
     */
    public static final int ERROR_CODE = 15;

    private static final long serialVersionUID = 1898479607903439110L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public UnknownDirectiveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public UnknownDirectiveException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
