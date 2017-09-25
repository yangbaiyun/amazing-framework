package org.amazing.framework.jdbc.core;

import org.amazing.framework.dao.InvalidDataAccessApiUsageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by john on 2017/9/25.
 */
public class PreparedStatementCreatorFactory {


    public static PreparedStatementCreator newPreparedStatementCreator(final String sql)
    {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                return ps;
            }

            public String getSql() {
                return sql;
            }
        };
    }


    public static PreparedStatementCreator newPreparedStatementCreator(String sql, int[] types, Object[] params) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, types);
        return pscf.newPreparedStatementCreator(params);
    }



    public static List sqlTypesToAnonymousParameterList(int[] types) {
        List l = new LinkedList();
        if (types != null) {
            for (int i = 0; i < types.length; i++) {
                l.add(new SqlParameter(types[i]));
            }
        }
        return l;
    }


    //---------------------------------------------------------------------
    // Instance data
    //---------------------------------------------------------------------
    /**
     * List of SqlParameter objects. May not be null.
     */
    private List declaredParameters = new LinkedList();

    /** The Sql, which won't change when the parameters change. */
    private String sql;

    //---------------------------------------------------------------------
    // Constructors
    //---------------------------------------------------------------------
    /**
     * Create a new factory. Will need to add parameters
     * via the addParameter() method or have no parameters
     */
    public PreparedStatementCreatorFactory(String sql) {
        this(sql, new LinkedList());
    }

    /**
     * Create a new factory with sql and parameters with the given JDBC types
     * @param sql SQL to execute
     * @param types int array of JDBC types
     */
    public PreparedStatementCreatorFactory(String sql, int[] types) {
        this(sql, sqlTypesToAnonymousParameterList(types) );
    }

    /**
     * Create a new factory with sql and the given parameters
     * @param sql SQL
     * @param declaredParameters list of SqlParameter objects
     */
    public PreparedStatementCreatorFactory(String sql, List declaredParameters) {
        this.sql = sql;
        this.declaredParameters = declaredParameters;
    }




    /**
     * Add a new declared parameter
     * Order of parameter addition is significant
     */
    public void addParameter(SqlParameter p) {
        declaredParameters.add(p);
    }


    //---------------------------------------------------------------------
    // Factory methods
    //---------------------------------------------------------------------
    /**
     * Return a new PreparedStatementCreator given these parameters
     * @param params parameter array. May be null.
     */
    public PreparedStatementCreator newPreparedStatementCreator(Object[] params) {
        return new PreparedStatementCreatorImpl((params != null) ? Arrays.asList(params) : new LinkedList());
    }

    /**
     * Return a new PreparedStatementCreator instance given this parameters.
     * @param params List of parameters. May be null.
     */
    public PreparedStatementCreator newPreparedStatementCreator(List params) {
        return new PreparedStatementCreatorImpl(params != null ? params : new LinkedList());
    }



    //---------------------------------------------------------------------
    // Inner classes
    //---------------------------------------------------------------------
    /**
     * PreparedStatementCreator implementation returned by this class
     */
    private class PreparedStatementCreatorImpl implements PreparedStatementCreator {
        private List parameters;

        /**
         * @param params list of SqlParameter objects. May not be null
         */
        private PreparedStatementCreatorImpl(List params) {
            this.parameters = params;
            if (parameters.size() != declaredParameters.size())
                throw new InvalidDataAccessApiUsageException("SQL='" + sql + "': given " + parameters.size() + " parameter but expected " + declaredParameters.size());
        }

        public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
            PreparedStatement ps = conn.prepareStatement(sql);

            // Set arguments: does nothing if there are no parameters
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter declaredParameter = (SqlParameter) PreparedStatementCreatorFactory.this.declaredParameters.get(i);
                // We need SQL type to be able to set null
                if (parameters.get(i) == null) {
                    ps.setNull(i + 1, declaredParameter.getType());
                }
                else {
                    // Documentation?

                    // PARAMETERIZE THIS TO A TYPE MAP INTERFACE?
                    switch (declaredParameter.getType()) {
                        case Types.VARCHAR :
                            ps.setString(i + 1, (String) parameters.get(i));
                            break;
                        //case Types. :
                        //	ps.setString(i + 1, (String) parameters.get(i));
                        //	break;
                        default :
                            ps.setObject(i + 1, parameters.get(i), declaredParameter.getType());
                            break;
                    }
                }
            }
            return ps;
        }

        public String getSql() {
            return sql;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer("PreparedStatementCreatorFactory.PreparedStatementCreatorImpl: sql={" + sql + "}: params={");
            for (int i = 0; i < parameters.size(); i++) {
                if (i > 0)
                    sbuf.append(",");
                sbuf.append(parameters.get(i));
            }
            return sbuf.toString() + "}";
        }
    }









}
