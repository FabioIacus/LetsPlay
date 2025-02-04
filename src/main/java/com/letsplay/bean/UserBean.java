package com.letsplay.bean;

import com.letsplay.exception.InputException;

public class UserBean {
    private String email;
    private String password;
    private String role;
    private String username;
    private String name;
    private String surname;

    public UserBean(String name, String surname, String email, String password, String username, String role) throws InputException {
        checkAttributes(name, surname, email, password, username, role);
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //getter
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole () {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    //setter
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setName(String name) {
        this.name = name;
    }

    private void checkAttributes(String name, String surname, String email, String password, String username, String role) throws InputException {
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || username.isEmpty() || role.isEmpty()) {
            throw new InputException("There are one or more empty fields!");
        }
        if (name.isBlank() || surname.isBlank() || email.isBlank() || password.isBlank() || username.isBlank() || role.isBlank()) {
            throw new InputException("There are one or more empty fields!");
        }
    }

}
