package com.letsplay.bean;

public class UserBean {
    private String email;
    private String password;
    private String role;
    private String username;
    private String name;
    private String surname;

    public UserBean(String username, String name, String surname, String email, String password, String role) {
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
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
