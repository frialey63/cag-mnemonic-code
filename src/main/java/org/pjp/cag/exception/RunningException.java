package org.pjp.cag.exception;

/**
 * This exception wraps the errors which may occur during the running of a program.
 * @author developer
 *
 */
public class RunningException extends RuntimeException {

    private static final long serialVersionUID = 2344136039363995508L;

    private final RunningError error;

    public RunningException(RunningError error) {
        super();
        this.error = error;
    }

    public RunningError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return String.format("ERR %2d %%4d %s", error.number(), error.description());
    }

}
