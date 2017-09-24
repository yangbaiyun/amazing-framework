package org.amazing.framework.dao;

/**
 * this class is the most important calss of this package.
 * define base Exception class.
 * define own Exception class to decouple with jdbc's SQLException,
 * because SQLException is a checked Exception,you must catch it no matter weather you need to resolve this Exception,
 * so I replace SQLException with the subclasses of DataAccessException.
 * Created by john on 2017/9/24.
 */
public class DataAccessException extends RuntimeException{


    public DataAccessException(String s)
    {
        super(s);
    }

    public DataAccessException(String s,Throwable t)
    {
        super(s,t);
    }


}
