package org.amazing.framework.jdbc.datasource;

import org.amazing.framework.dao.CannotGetJdbcConnectionException;
import org.amazing.framework.util.ThreadObjectManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by john on 2017/9/25.
 */
public abstract class DataSourceUtils {

    private  static final ThreadObjectManager threadObjectManager=new ThreadObjectManager();

    public static ThreadObjectManager getThreadObjectManager() {
        return threadObjectManager;
    }

    /*
    reurn for the given Connection is bound to the current thread,
    for the given datasource
     */
    public boolean isConnectionBoundToThread(Connection con, DataSource ds)
    {
        ConnectionHolder holder=(ConnectionHolder)threadObjectManager.getThreadObject(ds);
        return (holder!=null && holder.getConnection()==con);
    }



    public static Connection getConnection(DataSource ds) throws CannotGetJdbcConnectionException
    {
        ConnectionHolder holder=(ConnectionHolder)threadObjectManager.getThreadObject(ds);
        if(holder!=null)
            return holder.getConnection();
        else {
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                throw new CannotGetJdbcConnectionException("get connection failed for datasource:"+ds,e);
            }
        }
    }


    public static void closeConnectionIfNecessary(Connection con, DataSource ds)
    {}









}
