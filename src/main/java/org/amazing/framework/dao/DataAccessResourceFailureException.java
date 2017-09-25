package org.amazing.framework.dao;

/**
 * Created by john on 2017/9/24.
 */
public class DataAccessResourceFailureException extends DataAccessException{

    public DataAccessResourceFailureException(String s) {
        super(s);
    }

    public DataAccessResourceFailureException(String s, Throwable t) {
        super(s, t);
    }
}
