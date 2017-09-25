package org.amazing.framework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by john on 2017/9/25.
 */
public interface RowCallbackHandler {


    void processRow(ResultSet rs) throws SQLException;
}
