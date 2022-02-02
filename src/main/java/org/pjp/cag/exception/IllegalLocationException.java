package org.pjp.cag.exception;

/**
 * This exception occurs when an attempt is made to read/write a location in store with an invalid address.
 * @author developer
 *
 */
public final class IllegalLocationException extends RuntimeException {

    private static final long serialVersionUID = -5462381310870588141L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public IllegalLocationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public IllegalLocationException(String arg0) {
        super(arg0);
    }

}
