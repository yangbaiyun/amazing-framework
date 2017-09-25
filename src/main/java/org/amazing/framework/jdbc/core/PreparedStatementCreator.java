package org.amazing.framework.jdbc.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by john on 2017/9/25.
 */
public interface PreparedStatementCreator {

    PreparedStatement createPreparedStatement(Connection conn) throws SQLException;

    String getSql();


}
