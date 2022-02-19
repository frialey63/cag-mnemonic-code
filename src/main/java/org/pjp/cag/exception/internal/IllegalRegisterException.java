package org.pjp.cag.exception.internal;

/**
 * This exception occurs when an attempt is made to read/write a register in store with an invalid index.
 * @author developer
 *
 */
public final class IllegalRegisterException extends AbstractCAGException {

    private static final long serialVersionUID = 53619744823854462L;

    /**
     * @param message The message
     */
    public IllegalRegisterException(String message) {
        super(message);
    }

}
