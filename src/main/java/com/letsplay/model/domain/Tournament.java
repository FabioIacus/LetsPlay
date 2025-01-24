package com.letsplay.model.domain;

import java.util.Date;

public class Tournament {
    private String name;
    private String footballFacility;
    private String city;
    private String address;
    private Date startDate;
    private Date endDate;
    private int participationFee;
    private int numberTeams;
    private String prize;
    private String requirements;
    private Type type;
    private String managerEmail;

    public Tournament(String name, String footballFacility, String address, Date startDate, Date endDate, int participationFee, int numberTeams, String prize, String requirements, String city, Type type, String managerEmail) {
        this.name = name;
        this.footballFacility = footballFacility;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participationFee = participationFee;
        this.numberTeams = numberTeams;
        this.prize = prize;
        this.requirements = requirements;
        this.city = city;
        this.type = type;
        this.managerEmail = managerEmail;
    }

    public Tournament() {}

    public Tournament(String city) {
        this.city = city;
    }

    public Tournament(String name, String footballFacility, String address, java.sql.Date startDate, java.sql.Date endDate, Type type) {
        this.name = name;
        this.footballFacility = footballFacility;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getNumberTeams() {
        return numberTeams;
    }

    public void setNumberTeams(int numberTeams) {
        this.numberTeams = numberTeams;
    }

    public int getParticipationFee() {
        return participationFee;
    }

    public void setParticipationFee(int participationFee) {
        this.participationFee = participationFee;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFootballFacility() {
        return footballFacility;
    }

    public void setFootballFacility(String footballFacility) {
        this.footballFacility = footballFacility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
}
