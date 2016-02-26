package com.gtm.csims.log.appender;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

/**
 * JDBC数据库连接池实现的Log4j日志记录Appender
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class JDBCPoolAppender extends JDBCAppender {

    private DataSource ds;
    private String dsjndi;

    public JDBCPoolAppender() {
        super();
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public String getDsjndi() {
        return dsjndi;
    }

    public void setDsjndi(String dsjndi) {
        this.dsjndi = dsjndi;
        if (ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource) ctx.lookup(this.dsjndi);
            } catch (NamingException e) {
                e.printStackTrace();
                // e.printStackTrace();
                // System.out.println("Log4j JNDI context initial failed!");
                // errorHandler.error("Error initial jndi datasource", e,
                // ErrorCode.GENERIC_FAILURE);
            }
        }
    }

    @Override
    protected void closeConnection(Connection con) {
        super.closeConnection(con);
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorHandler.error("Error closing connection", e,
                    ErrorCode.GENERIC_FAILURE);
        }
    }

    @Override
    protected Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    protected String getLogStatement(LoggingEvent event) {
        return super.getLogStatement(event);
    }
}
