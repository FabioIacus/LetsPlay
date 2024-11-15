package com.letsplay.model.domain;

public class User {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String name, String surname, Role role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String username, String email, String password, String name, String surname, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

}
