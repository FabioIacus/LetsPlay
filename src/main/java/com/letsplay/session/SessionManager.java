package com.letsplay.session;

import com.letsplay.model.domain.User;

public class SessionManager {
    private static SessionManager instance = null;
    private User currentUser;

    //applicazione del pattern Singleton sulla sessione
    protected SessionManager() {
        currentUser = null;
    }

    public static SessionManager getInstance() {
        if (SessionManager.instance == null)
            SessionManager.instance = new SessionManager();
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login(User user) {
        currentUser = user;
    }

    public void logout() {
        currentUser = null;
    }
}
