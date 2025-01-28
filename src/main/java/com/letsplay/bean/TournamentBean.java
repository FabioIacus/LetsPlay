package com.letsplay.bean;

import java.util.Date;

public class TournamentBean {
    private String name;
    private String footballFacility;
    private String city;
    private String address;
    private Date startDate;
    private Date endDate;
    private int participationFee;
    private int numTeams;
    private String prize;
    private String requirements;
    private String type;
    private String managerEmail;

    public TournamentBean(String name, String footballFacility, String city, String address, Date startDate, Date endDate, int participationFee) {
        this.name = name;
        this.footballFacility = footballFacility;
        this.city = city;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participationFee = participationFee;
    }

    public String getName() {
        return name;
    }

    public String getFootballFacility() {
        return footballFacility;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getParticipationFee() {
        return participationFee;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public String getPrize() {
        return prize;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getType() {
        return type;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
}
