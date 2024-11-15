package com.letsplay.controller;

import com.letsplay.bean.UserBean;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.RegistrationException;
import com.letsplay.model.DAO.UserDAO;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;

import java.sql.SQLException;

public class SignUpController {
    public int signUp(UserBean userBean) throws RegistrationException {
        User user = new User(userBean.getUsername(), userBean.getEmail(), userBean.getPassword(), userBean.getName(), userBean.getSurname(), Role.valueOf(userBean.getRole().toUpperCase()));
        int result;
        try {
            result = new UserDAO().signUp(user);
        } catch (DAOException | SQLException e) {
            throw new RegistrationException("Registration failed");
        }

        return result;
    }
}
