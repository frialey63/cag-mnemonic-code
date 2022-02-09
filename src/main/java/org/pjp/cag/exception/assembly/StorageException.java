package org.pjp.cag.exception.assembly;

import org.pjp.cag.exception.AbstractCAGException;

/**
 * This exception occurs when the Assembler is unable to store a correctly parsed order / number / character.
 * @author developer
 *
 */
public final class StorageException extends AbstractCAGException {
    /**
     * The error code.
     */
    public static final int ERROR_CODE = 14;

    private static final long serialVersionUID = 5605868128242958058L;

    /**
     * @param message The message
     * @param cause The cause
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message The message
     */
    public StorageException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

}
