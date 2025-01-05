package com.letsplay.model.domain;

public enum RequestStatus {
    ACCEPTED("accepted"),
    PENDING("pending"),
    REJECTED("rejected");

    private final String id;

    RequestStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
