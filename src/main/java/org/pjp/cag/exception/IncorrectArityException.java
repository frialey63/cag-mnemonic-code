package org.pjp.cag.exception;

/**
 * This exception occurs when an Order is encountered with incorrect arity, i.e. the number of arguments supplied is incorrect.
 * @author developer
 *
 */
public final class IncorrectArityException extends RuntimeException {

    private static final long serialVersionUID = 4828019001253333230L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public IncorrectArityException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public IncorrectArityException(String arg0) {
        super(arg0);
    }

}
