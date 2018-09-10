package com.example.intouch.ifaces;

import com.example.intouch.exceptions.DAOException;
import com.example.intouch.model.beans.User;

public interface IUserDAO {

    public User getUser(String name, String password) throws DAOException;
    public User setUser(String name, String password, String email) throws DAOException;
}
