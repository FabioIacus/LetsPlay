package com.letsplay.model.domain;

import com.letsplay.exception.EmailException;
import com.letsplay.pattern.NotificationSystem;
import com.letsplay.pattern.Subject;

public class Registration extends Subject {
    private String customerEmail;
    private String managerEmail;
    private String team;
    private int numPlayers;
    private String captain;
    private String tournament;
    private RequestStatus status;
    private String message;

    public Registration(String customerEmail, String managerEmail, String team, int numPlayers, String captain, String tournament) throws EmailException {
        this.customerEmail = customerEmail;
        this.managerEmail = managerEmail;
        this.team = team;
        this.numPlayers = numPlayers;
        this.captain = captain;
        this.tournament = tournament;
        this.status = RequestStatus.PENDING;
        NotificationSystem notificationSystem = new NotificationSystem(this);
        this.attach(notificationSystem);
    }

    public Registration(String customerEmail, String team, int numPlayers, String captain, String managerEmail, RequestStatus status, String message, String tournament) {
        this.customerEmail = customerEmail;
        this.team = team;
        this.numPlayers = numPlayers;
        this.captain = captain;
        this.managerEmail = managerEmail;
        this.status = status;
        this.message = message;
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

    public void notifyManager() {
        super.notifyObservers();
    }
}
