package com.letsplay.controller;

import com.letsplay.bean.UserBean;
import com.letsplay.exception.DAOException;
import com.letsplay.session.SessionManager;
import com.letsplay.model.dao.UserDAO;
import com.letsplay.model.domain.User;

import java.sql.SQLException;

public class LoginController {

    public Object[] login(UserBean userBean) throws SQLException, DAOException {
        //creazione oggetto 'credenziali' che verrà passato al DAO
        User credentials  = new User(userBean.getEmail(), userBean.getPassword());

        //per effettuare il login chiamo il DAO
        User user = new UserDAO().login(credentials);

        //invoco il gestore delle sessioni per avere l'unica istanza
        SessionManager sessionManager = SessionManager.getInstance();

        //istanzio utente corrente
        User currentUser = new User(user.getUsername(), user.getEmail(), user.getName(), user.getSurname(), user.getRole());

        //il session manager tiene traccia dell'utente corrente
        sessionManager.login(currentUser);

        return new Object[] {currentUser.getUsername(), currentUser.getRole().toString()};
    }

    public void logout() {
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.logout();
    }
}
