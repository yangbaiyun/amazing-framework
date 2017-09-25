package org.amazing.framework.jdbc.core;

import org.amazing.framework.dao.DataAccessException;
import org.amazing.framework.jdbc.datasource.DataSourceUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by john on 2017/9/25.
 */
public class JdbcTemplate {

    public static final PreparedStatementSetter PREPARE_STATEMENT = new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
            // do nothing
        }
    };

    protected final Log logger = LogFactory.getLog(getClass());

    private DataSource dataSource;

    public JdbcTemplate() {
    }


    public JdbcTemplate(DataSource dataSource) throws DataAccessException{
        setDataSource(dataSource);
    }

    public void setDataSource(DataSource dataSource) throws DataAccessException{
        if (dataSource == null) {
            throw new DataAccessException("dataSource is null");
        }
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void query(PreparedStatementCreator psc, RowCallbackHandler callbackHandler) throws DataAccessException {
        doWithResultSetFromPreparedQuery(psc, callbackHandler);
    }

    public void doWithResultSetFromPreparedQuery(PreparedStatementCreator psc, RowCallbackHandler rse) throws DataAccessException {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DataSourceUtils.getConnection(this.dataSource);
            PreparedStatement ps = psc.createPreparedStatement(con);
            if (logger.isInfoEnabled())
                logger.info("Executing SQL query using PreparedStatement: [" + psc + "]");
            rs = ps.executeQuery();

            rse.processRow(rs);


            rs.close();
            ps.close();

        }
        catch (SQLException ex) {
            throw new DataAccessException("JdbcTemplate.query(psc) with PreparedStatementCreator [" + psc + "]",ex);
        }
        finally {
           // DataSourceUtils.closeConnectionIfNecessary(con, this.dataSource);
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new DataAccessException("close connection failed",e);
                }
        }




    }


}
