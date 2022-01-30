package org.pjp.cag.exception;

public class IllegalLocationException extends RuntimeException {

    private static final long serialVersionUID = -5462381310870588141L;

    public IllegalLocationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public IllegalLocationException(String arg0) {
        super(arg0);
    }

}
