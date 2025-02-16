package com.letsplay.model.domain;

public enum RequestStatus {
    ACCEPTED("Accepted"),
    PENDING("Pending"),
    REJECTED("Rejected");

    private final String id;

    RequestStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
