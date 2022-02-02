package org.pjp.cag.exception;

/**
 * This exception occurs when a line of program text cannot be parsed, i.e. the line cannot be matched to any regex.
 * @author developer
 *
 */
public final class ParseException extends RuntimeException {

    private static final long serialVersionUID = -1246997111517736112L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public ParseException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public ParseException(String arg0) {
        super(arg0);
    }

}
