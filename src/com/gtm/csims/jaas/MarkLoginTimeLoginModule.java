package com.gtm.csims.jaas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import net.sf.jguard.ext.authentication.loginmodules.UserLoginModule;
import net.sf.jguard.ext.authentication.manager.AuthenticationManagerFactory;
import net.sf.jguard.ext.authentication.manager.JdbcAuthenticationManager;
import net.sf.jguard.ext.database.ConnectionFactory;

/**
 * LoginModule dedicated to Databases.
 * 
 * 增加记录当前用户最近登陆时间
 * 
 * @author <a href="mailto:diabolo512@users.sourceforge.net">Charles Gay</a>
 * @see LoginModule
 */
public class MarkLoginTimeLoginModule extends UserLoginModule implements
        LoginModule {

    static public final String CRED_VALUE = "cred_value";
    static public final String CRED_NAME = "cred_name";
    static public final String NAME = "name";

    private static final String ID = "id";
    private static final Logger logger = Logger
            .getLogger(MarkLoginTimeLoginModule.class.getName());

    private int userId = -1;

    private ConnectionFactory connectionFactory;

    private Properties props;

    /**
     * initialize database-related loginModule specifying 'digestAlgorithm',
     * tables names,'applicationName', and connection parameters.
     * 
     * @param subj
     * @param cbkHandler
     * @param sState
     * @param opts
     */
    @SuppressWarnings("unchecked")
    public void initialize(Subject subj, CallbackHandler cbkHandler,
            Map sState, Map opts) {
        super.initialize(subj, cbkHandler, sState, opts);

        JdbcAuthenticationManager authManager = (JdbcAuthenticationManager) AuthenticationManagerFactory
                .getAuthenticationManager();
        props = authManager.getProperties();
        connectionFactory = authManager.getConnectionFactory();

    }

    /**
     * verify either user is registered or not.
     * 
     * @see javax.security.auth.spi.LoginModule#login()
     */
    public boolean login() throws LoginException {
        super.login();
        userId = getUserID(login);
        if (!skipPasswordCheck) {
            boolean passwordValidated = false;
            try {
                passwordValidated = validatePassword(new String(password));
            } catch (LoginException e) {
                loginOK = false;
                throw e;
            }
            logger.finest("password validation =" + passwordValidated
                    + " for userId=" + userId);
        }
        // 记录当前登陆人登陆时间
        try {
            this.writeLoginDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 更新当前用户最近登陆系统时间
     */
    private void writeLoginDate() {
        // guest用户不记录登陆时间
        if (!login.trim().equalsIgnoreCase("guest")) {
            Connection conn2 = null;
            PreparedStatement pst2 = null;
            ResultSet rs2 = null;
            try {
                conn2 = connectionFactory.getConnection();
                pst2 = conn2
                        .prepareStatement("SELECT ID FROM BS_TEST WHERE name = ?");
                pst2.setString(1, login);
                rs2 = pst2.executeQuery();
                if (rs2.next()) {
                    pst2 = conn2
                            .prepareStatement("UPDATE BS_TEST SET LOGINDATE = ? WHERE name = ?");
                    pst2.setTimestamp(1, new Timestamp(System
                            .currentTimeMillis()));
                    // pst2.setDate(3, new java.sql.Date(now.getTime()));
                    pst2.setString(2, login);
                    pst2.execute();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            } finally {
                try {
                    rs2.close();
                    pst2.close();
                    conn2.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean validatePassword(String cryptedPassword)
            throws LoginException {
        boolean authenticated;
        Connection conn2 = null;
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        try {
            conn2 = connectionFactory.getConnection();
            pst2 = conn2.prepareStatement((String) props
                    .get("USER_PASSWORD_EXIST"));
            pst2.setInt(1, userId);
            pst2.setString(2, cryptedPassword);
            rs2 = pst2.executeQuery();
            authenticated = rs2.next();
            if (authenticated == false) {
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest(" password does not match ");
                }
                throw new FailedLoginException("login.password.does.not.match");
            }
        } catch (SQLException e2) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.severe("login() -  connection in "
                        + this.getClass().getName() + " failed "
                        + e2.getMessage());
                logger.severe("USER_PASSWORD_EXIST="
                        + props.get("USER_PASSWORD_EXIST"));
            }
            throw new LoginException(e2.getMessage());

        } finally {
            try {
                rs2.close();
                pst2.close();
                conn2.close();
            } catch (SQLException e2) {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger
                            .severe("login() -  connection in MySQLLoginModule failed "
                                    + e2.getMessage());
                }
            }
        }
        return authenticated;
    }

    private int getUserID(String login) throws LoginException {
        boolean authenticated;
        // check the login and grab the user id
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection conn = null;
        int userId = -1;
        try {
            conn = connectionFactory.getConnection();
            pst = conn.prepareStatement((String) props.get("USER_LOGIN_EXIST"));
            pst.setString(1, login);
            rs = pst.executeQuery();
            authenticated = rs.next();

            if (authenticated == false) {
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest(" user " + login + " does not exists ");
                }
                throw new FailedLoginException("login.user.does.not.exist");
            }

            userId = rs.getInt(MarkLoginTimeLoginModule.ID);

        } catch (SQLException e1) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.severe("login() -  connection " + e1.getMessage());
            }
            throw new LoginException(e1.getMessage());
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e2) {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.severe("login() -  connection  failed "
                            + e2.getMessage());
                }
            }
        }

        return userId;
    }

    /**
     * add Principals and Public/Private credentials to Subject.
     * 
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @SuppressWarnings("unchecked")
    public boolean commit() throws LoginException {
        if (!loginOK) {
            return false;
        }
        try {
            Subject subj = JdbcAuthenticationManager.getUser(userId);
            // we copy the content into the real Subject
            subject.getPrincipals().addAll(subj.getPrincipals());
            subject.getPublicCredentials().addAll(subj.getPublicCredentials());
            subject.getPrivateCredentials()
                    .addAll(subj.getPrivateCredentials());
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest(" user authenticated subject=" + subject);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "commit()", e);
            throw new LoginException(e.getMessage());
        }

        return true;
    }
}