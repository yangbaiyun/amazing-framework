package org.amazing.framework.jdbc.datasource;

import java.sql.Connection;

/**
 * Created by john on 2017/9/24.
 */
public class ConnectionHolder {

    private final Connection connection;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }



}
