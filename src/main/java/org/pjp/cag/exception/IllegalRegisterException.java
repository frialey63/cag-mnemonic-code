package org.pjp.cag.exception;

public class IllegalRegisterException extends RuntimeException {

    private static final long serialVersionUID = 53619744823854462L;

    public IllegalRegisterException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public IllegalRegisterException(String arg0) {
        super(arg0);
    }

}
