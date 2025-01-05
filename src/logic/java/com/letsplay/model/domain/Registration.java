package com.letsplay.model.domain;

public class Registration {
    private String customerEmail;
    private String managerEmail;
    private String team;
    private int numPlayers;
    private String captain;
    private String tournament;
    private RequestStatus status;
    private String message;

    public Registration(String customerEmail, String managerEmail, String team, int numPlayers, String captain, String tournament, RequestStatus status, String message) {
        this.customerEmail = customerEmail;
        this.managerEmail = managerEmail;
        this.team = team;
        this.numPlayers = numPlayers;
        this.captain = captain;
        this.tournament = tournament;
        this.status = status;
        this.message = message;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public String getTeam() {
        return team;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public String getCaptain() {
        return captain;
    }

    public String getTournament() {
        return tournament;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
