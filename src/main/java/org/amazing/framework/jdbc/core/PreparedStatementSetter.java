package org.amazing.framework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by john on 2017/9/25.
 */
public interface PreparedStatementSetter {

    void setValues(PreparedStatement ps) throws SQLException;

}
