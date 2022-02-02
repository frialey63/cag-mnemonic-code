package org.pjp.cag.exception;

/**
 * This exception occurs if a word is mis-interpreted, i.e. it contains a number but it was interpreted as an Order.
 * @author developer
 *
 */
public final class FaultyWordException extends RuntimeException {

    private static final long serialVersionUID = -4001170531420888949L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public FaultyWordException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public FaultyWordException(String arg0) {
        super(arg0);
    }

}
