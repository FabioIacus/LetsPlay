package com.letsplay.model.domain;

public enum Type {
    CALCIOA5("Calcio a 5"),
    CALCIOA8("Calcio a 8"),
    CALCIOA11("Calcio a 11");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
