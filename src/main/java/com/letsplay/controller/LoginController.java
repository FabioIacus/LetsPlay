package com.letsplay.controller;

import com.letsplay.bean.UserBean;
import com.letsplay.exception.DAOException;
import com.letsplay.model.DAO.SessionManager;
import com.letsplay.model.DAO.UserDAO;
import com.letsplay.model.domain.User;

import java.sql.SQLException;

public class LoginController {

    public void login(UserBean userBean) throws SQLException, DAOException {
        //creo l'entità utente
        User cred  = new User(userBean.getEmail(), userBean.getPassword());

        //per effettuare il login chiamo il DAO
        User user = new UserDAO().login(cred);

        //invoco il gestore delle sessioni per avere l'unica istanza
        SessionManager sessionManager = SessionManager.getInstance();

        //setto l'utente corrente
        User currentUser = new User(user.getUsername(), user.getEmail(), user.getName(), user.getSurname(), user.getRole());

        //il session manager tiene traccia dell'utente corrente
        sessionManager.login(currentUser);
    }

    public void logout() {

    }
}
