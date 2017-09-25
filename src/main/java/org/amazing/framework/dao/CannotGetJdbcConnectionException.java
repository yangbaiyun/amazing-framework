package org.amazing.framework.dao;

/**
 * Created by john on 2017/9/24.
 */
public class CannotGetJdbcConnectionException extends DataAccessException{
    public CannotGetJdbcConnectionException(String s) {
        super(s);
    }

    public CannotGetJdbcConnectionException(String s, Throwable t) {
        super(s, t);
    }
}
