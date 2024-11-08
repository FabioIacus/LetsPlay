package com.letsplay.bean;

import com.letsplay.model.domain.Role;

public class UserBean {
    private final String email;
    private final String password;
    //private final Role role;
    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
