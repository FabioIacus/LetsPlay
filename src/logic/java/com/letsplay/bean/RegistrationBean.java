package com.letsplay.bean;

import com.letsplay.model.domain.RequestStatus;

public class RegistrationBean {
    private String customerEmail;
    private String managerEmail;
    private String team;
    private int numPlayers;
    private String captain;
    private String tournament;
    private RequestStatus status;
    private String message;

    public RegistrationBean(String customerEmail, String managerEmail, String tournament) {
        this.customerEmail = customerEmail;
        this.managerEmail = managerEmail;
        this.tournament = tournament;
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

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
