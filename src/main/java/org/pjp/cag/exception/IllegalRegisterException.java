package org.pjp.cag.exception;

/**
 * This exception occurs when an attempt is made to read/write a register in store with an invalid index.
 * @author developer
 *
 */
public final class IllegalRegisterException extends AbstractCAGException {

    /**
     * The error code.
     */
    public static final int ERROR_CODE = 3;

    private static final long serialVersionUID = 53619744823854462L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public IllegalRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public IllegalRegisterException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
