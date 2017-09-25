package org.amazing.framework.dao;

/**
 * Created by john on 2017/9/25.
 */
public class InvalidDataAccessApiUsageException extends  DataAccessException {
    public InvalidDataAccessApiUsageException(String s) {
        super(s);
    }

    public InvalidDataAccessApiUsageException(String s, Throwable t) {
        super(s, t);
    }
}
