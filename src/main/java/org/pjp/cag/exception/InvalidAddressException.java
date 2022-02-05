package org.pjp.cag.exception;

/**
 * This exception occurs when an AddressDirective is encountered with an invalid address.
 * @author developer
 *
 */
public final class InvalidAddressException extends RuntimeException {

    private static final long serialVersionUID = -5374209238127912597L;

    /**
     * @param arg0 The message
     * @param arg1 The throwable
     */
    public InvalidAddressException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0 The message
     */
    public InvalidAddressException(String arg0) {
        super(arg0);
    }

}
