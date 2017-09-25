package org.amazing.framework.dao;

/**
 * Created by john on 2017/9/24.
 */
public class CleanupFailureDataAccessException extends DataAccessException {
    public CleanupFailureDataAccessException(String s) {
        super(s);
    }

    public CleanupFailureDataAccessException(String s, Throwable t) {
        super(s, t);
    }
}
