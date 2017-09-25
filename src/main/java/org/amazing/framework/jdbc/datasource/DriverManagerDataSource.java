package org.amazing.framework.jdbc.datasource;

import org.amazing.framework.dao.CannotGetJdbcConnectionException;
import org.amazing.framework.dao.ClassNotFountException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by john on 2017/9/24.
 */
public class DriverManagerDataSource extends AbstractDataSource{

    protected final Log logger= LogFactory.getLog(this.getClass());

    private String driverClassName = "";

    private String url = "";

    private String username = "";

    private String password = "";

    public DriverManagerDataSource() {
    }

    public DriverManagerDataSource(String driverName, String url, String user, String password)
            throws CannotGetJdbcConnectionException {
        setDriverClassName(driverName);
        setUrl(url);
        setUsername(user);
        setPassword(password);
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        try {
            Class.forName(this.driverClassName);
            logger.debug("loaded driver class :"+this.driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFountException("can not find DriverClass:"+this.driverClassName);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return getConnection(this.username, this.password);
    }

    public Connection getConnection(String username,String password) throws SQLException {
        logger.debug("create connection instance:"+this.url);
        Connection con= DriverManager.getConnection(this.url,username,password);
        con.setAutoCommit(true);
        return con;
    }



}
