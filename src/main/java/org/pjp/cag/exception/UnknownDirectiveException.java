package org.pjp.cag.exception;

/**
 * This exception occurs when an unknown directive is encountered.
 * @author developer
 *
 */
public final class UnknownDirectiveException extends RuntimeException {

    private static final long serialVersionUID = 1898479607903439110L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public UnknownDirectiveException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public UnknownDirectiveException(String arg0) {
        super(arg0);
    }

}
