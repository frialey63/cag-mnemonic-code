package org.pjp.cag.exception;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 5605868128242958058L;

    public StorageException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public StorageException(String arg0) {
        super(arg0);
    }

}
