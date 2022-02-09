package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when an AddressDirective is encountered with an invalid address.
 * @author developer
 *
 */
public final class InvalidAddressException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 12;

    private static final long serialVersionUID = -5374209238127912597L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public InvalidAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public InvalidAddressException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
