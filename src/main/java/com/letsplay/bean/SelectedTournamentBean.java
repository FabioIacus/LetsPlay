package com.letsplay.bean;

public class SelectedTournamentBean {
    private String tournament;
    private String footballFacility;
    public SelectedTournamentBean(String tournament, String footballFacility) {
        this.tournament = tournament;
        this.footballFacility = footballFacility;
    }
    public String getTournament() {
        return tournament;
    }
    public String getFootballFacility() {
        return footballFacility;
    }
}
