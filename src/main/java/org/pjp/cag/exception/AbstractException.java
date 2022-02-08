package org.pjp.cag.exception;

/**
 * The abstract exception containing common error code and address attributes, from which all other CAG exceptions are derived.
 * @author developer
 *
 */
public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = -2604761222265709513L;

    private final int errorCode;

    private final int address;

    /**
     * @param errorCode The error code
     * @param address The address
     */
    public AbstractException(int errorCode, int address) {
        super();
        this.errorCode = errorCode;
        this.address = address;
    }

    /**
     * @return The error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @return The address
     */
    public int getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("ERR %2d %4d", errorCode, address);
    }

}
