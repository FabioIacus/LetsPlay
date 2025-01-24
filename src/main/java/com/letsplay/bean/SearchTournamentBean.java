package com.letsplay.bean;

import com.letsplay.exception.InputException;

public class SearchTournamentBean {
    String city;
    public SearchTournamentBean(String city) throws InputException {
        checkAttribute(city);
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    private void checkAttribute(String city) throws InputException {
        if (city.isEmpty() || city.isBlank()) {
            throw new InputException("You must specify a city");
        }
    }
}
