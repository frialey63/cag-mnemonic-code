package org.pjp.cag.exception;

public class FaultyWordException extends RuntimeException {

    private static final long serialVersionUID = -4001170531420888949L;

    public FaultyWordException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FaultyWordException(String arg0) {
        super(arg0);
    }

}
