package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when a line of program text cannot be parsed by the Assembler, i.e. the line cannot be matched to any regex.
 * @author developer
 *
 */
public final class ParseException extends AbstractCAGException {
    /**
     * The error code.
     */
    public static final int ERROR_CODE = 13;

    private static final long serialVersionUID = -1246997111517736112L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public ParseException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
