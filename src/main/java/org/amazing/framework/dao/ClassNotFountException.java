package org.amazing.framework.dao;

/**
 * Created by john on 2017/9/24.
 */
public class ClassNotFountException extends DataAccessException{
    public ClassNotFountException(String s) {
        super(s);
    }

    public ClassNotFountException(String s, Throwable t) {
        super(s, t);
    }
}
