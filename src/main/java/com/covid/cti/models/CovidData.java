package com.covid.cti.models;

public class CovidData {

    private String state;
    private int confirmed;
    private int confirmedRise;
    private int active;
    private int recovered;
    private int deaths;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getConfirmedRise() {
        return confirmedRise;
    }

    public void setConfirmedRise(int confirmedRise) {
        this.confirmedRise = confirmedRise;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}
