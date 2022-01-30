package org.pjp.cag.exception;

public class ParseException extends RuntimeException {

    private static final long serialVersionUID = -1246997111517736112L;

    public ParseException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ParseException(String arg0) {
        super(arg0);
    }

}
