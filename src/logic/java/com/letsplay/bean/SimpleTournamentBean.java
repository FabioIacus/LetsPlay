package com.letsplay.bean;

import java.util.Date;

public class SimpleTournamentBean {
    private String name;
    private Date startDate;
    private Date endDate;
    private String footballFacility;
    private String address;
    private String type;

    public SimpleTournamentBean(String name, Date startDate, Date endDate, String footballFacility, String address, String type) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.footballFacility = footballFacility;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFootballFacility() {
        return footballFacility;
    }

    public void setFootballFacility(String footballFacility) {
        this.footballFacility = footballFacility;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
