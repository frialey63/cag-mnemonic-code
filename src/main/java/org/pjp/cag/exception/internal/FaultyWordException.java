package org.pjp.cag.exception.internal;

/**
 * This exception occurs if a word is mis-interpreted, e.g. it contains a number but it was interpreted as an Instruction.
 * @author developer
 *
 */
public final class FaultyWordException extends AbstractCAGException {

    private static final long serialVersionUID = -4001170531420888949L;

    /**
     * @param message The message
     */
    public FaultyWordException(String message) {
        super(message);
    }

}
