package com.jdbc;


import org.amazing.framework.jdbc.core.PreparedStatementCreator;
import org.amazing.framework.jdbc.core.PreparedStatementCreatorFactory;
import org.amazing.framework.jdbc.core.RowCallbackHandler;
import org.amazing.framework.jdbc.datasource.DriverManagerDataSource;
import org.junit.Test;

import java.sql.*;

/**
 * Created by john on 2017/9/24.
 */
public class JdbcTemplate {
    private static  String name="";

    @Test
    public void test()
    {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "123";
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource(driver,url,username,password);

         org.amazing.framework.jdbc.core.JdbcTemplate jdbcTemplate=new org.amazing.framework.jdbc.core.JdbcTemplate();
        jdbcTemplate.setDataSource(driverManagerDataSource);
        PreparedStatementCreator psc=new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement("select name from student where id=?");
                ps.setInt(1,1048);
                return ps;
            }

            @Override
            public String getSql() {
                return null;
            }
        };

        RowCallbackHandler rch=new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                         while(rs.next())
                         {
                             System.out.println(rs.getString(1));
                         }
            }
        };

        jdbcTemplate.query(PreparedStatementCreatorFactory.newPreparedStatementCreator("select name from student"),rch);
        System.out.println(name+":00000000000");

    }

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
