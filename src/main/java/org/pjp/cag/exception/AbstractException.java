package org.pjp.cag.exception;

/**
 * TODO develop error handling, maybe assembler should be checked and interpreter unchecked
 * The abstract exception containing common error code and address attributes, from which all other CAG exceptions are derived.
 * @author developer
 *
 */
public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = -2604761222265709513L;

    private final int errorCode;

    /**
     * @param errorCode The error code
     */
    public AbstractException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * @return The error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return String.format("ERR %2d", errorCode);
    }

}
