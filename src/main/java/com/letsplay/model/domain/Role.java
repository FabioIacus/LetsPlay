package com.letsplay.model.domain;

public enum Role {
    CUSTOMER("customer"),
    MANAGER("manager");

    private final String id;

    Role(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
