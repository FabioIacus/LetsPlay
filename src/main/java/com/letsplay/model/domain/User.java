package com.letsplay.model.domain;

public class User {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;

    public User(String username, String email, String password, String name, String surname, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

}
