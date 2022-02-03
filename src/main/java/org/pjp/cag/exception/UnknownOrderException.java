package org.pjp.cag.exception;

/**
 * This exception occurs when an Order is encountered with an unknown mnemonic.
 * @author developer
 *
 */
public final class UnknownOrderException extends RuntimeException {

    private static final long serialVersionUID = 8161797116022732760L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public UnknownOrderException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public UnknownOrderException(String arg0) {
        super(arg0);
    }

}
