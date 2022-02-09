package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when an Order is encountered with an unknown mnemonic.
 * @author developer
 *
 */
public final class UnknownOrderException extends AbstractCAGException {
    /**
     * The error code.
     */
    public static final int ERROR_CODE = 16;

    private static final long serialVersionUID = 8161797116022732760L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public UnknownOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public UnknownOrderException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
