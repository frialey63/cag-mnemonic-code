package org.pjp.cag.error;

/**
 * This exception wraps the errors which may occur during the running of a program.
 * @author developer
 *
 */
public class RunningException extends RuntimeException {

    private static final long serialVersionUID = 2344136039363995508L;

    public RunningException(RunningError error) {
        super(error.toString());
    }


}
