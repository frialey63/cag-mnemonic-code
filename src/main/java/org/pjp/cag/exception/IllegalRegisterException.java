package org.pjp.cag.exception;

/**
 * This exception occurs when an attempt is made to read/write a register in store with an invalid number.
 * @author developer
 *
 */
public final class IllegalRegisterException extends RuntimeException {

    private static final long serialVersionUID = 53619744823854462L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public IllegalRegisterException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public IllegalRegisterException(String arg0) {
        super(arg0);
    }

}
