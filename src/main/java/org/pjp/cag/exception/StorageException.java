package org.pjp.cag.exception;

/**
 * This exception occurs when the assembler is unable to store a correctly parsed order / number / character.
 * @author developer
 *
 */
public final class StorageException extends RuntimeException {

    private static final long serialVersionUID = 5605868128242958058L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public StorageException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public StorageException(String arg0) {
        super(arg0);
    }

}
