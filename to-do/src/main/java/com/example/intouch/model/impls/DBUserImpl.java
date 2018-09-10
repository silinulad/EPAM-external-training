package com.example.intouch.model.impls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ConstantsSQL;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.IUserDAO;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.db.ConnectionDB;

public class DBUserImpl implements IUserDAO {

    private final ConnectionDB connect = new ConnectionDB();

    @Override
    public User getUser(String login, String password) throws DAOException {

        final int REQUEST_INDEX_LOGIN = 1;
        final int REQUEST_INDEX_PASSWORD = 2;

        final int RESPONSE_INDEX_ID = 1;
        final int RESPONSE_INDEX_LOGIN = 2;
        final int RESPONSE_INDEX_PASSWORD = 3;
        final int RESPONSE_INDEX_EMAIL = 4;

        Connection connection = null;
        PreparedStatement psSelectUser = null;
        ResultSet resultSet = null;
        
        try {
            connection = connect.getConnection();
            psSelectUser = connection.prepareStatement(ConstantsSQL.SELECT_USER);
           
            psSelectUser.setString(REQUEST_INDEX_LOGIN, login);
            psSelectUser.setString(REQUEST_INDEX_PASSWORD, password);
           
            resultSet = psSelectUser.executeQuery();
           
            if (resultSet.next()) {
                User resultUser = new User();
                
                resultUser.setId(resultSet.getInt(RESPONSE_INDEX_ID));
                resultUser.setLogin(resultSet.getString(RESPONSE_INDEX_LOGIN));
                resultUser.setPassword(resultSet.getString(RESPONSE_INDEX_PASSWORD));
                resultUser.setEmail(resultSet.getString(RESPONSE_INDEX_EMAIL));
                return resultUser;
           
            } else {
                throw new DAOException(ErrorMessage.ERROR_LOGIN_OR_PASSWORD_INCORRECT);
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
        } catch (SQLException ex) {
            throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
        } finally {
        	connect.closeResources(resultSet, psSelectUser, connection);
            
        }
    }

    @Override
    public User setUser(String login, String password, String email) throws DAOException {

        final int INDEX_GENERATED_KEY = 1;

        final int INDEX_LOGIN = 1;
        final int INDEX_PASSWORD = 2;
        final int INDEX_EMAIL = 3;

        if (Constants.USER_GUEST.toLowerCase().equals(login.toLowerCase())) {
            throw new DAOException(ErrorMessage.ERROR_LOGIN);
        }
        
        Connection connection = null;
        PreparedStatement psSelectLogin = null;
        PreparedStatement psInsertUser = null;
        ResultSet resultSet = null;
        ResultSet generatedKeys = null;
        
        try {
            int userId = Constants.ID_EMPTY;
            
            connection = connect.getConnection();
            psSelectLogin = connection.prepareStatement(ConstantsSQL.SELECT_LOGIN);
            psSelectLogin.setString(INDEX_LOGIN, login);
            
            psInsertUser = connection.prepareStatement(ConstantsSQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            psInsertUser.setString(INDEX_LOGIN, login);
            psInsertUser.setString(INDEX_PASSWORD, password);
            psInsertUser.setString(INDEX_EMAIL, email);
          
            synchronized (DBUserImpl.class) {
                resultSet = psSelectLogin.executeQuery();
                if (resultSet.next()) {
                    throw new DAOException(ErrorMessage.ERROR_LOGIN);
                }
                psInsertUser.executeUpdate();
                generatedKeys = psInsertUser.getGeneratedKeys();
            }
           
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(INDEX_GENERATED_KEY);
            }
            
            return new User(userId, login, password, email);
       
        } catch (ClassNotFoundException | IOException ex) {
            throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
        } catch (SQLException ex) {
            throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
        } finally {
        	connect.closeResources(generatedKeys, psInsertUser, resultSet, psSelectLogin, connection);
        }
    }
}
