package com.letsplay.controller;

import com.letsplay.bean.UserBean;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.model.dao.UserDAO;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;

import java.sql.SQLException;

public class SignUpController {
    public int signUp(UserBean userBean) throws DatabaseException {
        User user = new User(userBean.getUsername(), userBean.getEmail(), userBean.getPassword(), userBean.getName(), userBean.getSurname(), Role.valueOf(userBean.getRole().toUpperCase()));
        int result;
        try {
            result = new UserDAO().signUp(user);
        } catch (DAOException | SQLException e) {
            throw new DatabaseException("Registration failed");
        }
        return result;
    }
}