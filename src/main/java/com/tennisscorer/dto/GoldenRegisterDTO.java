package com.tennisscorer.dto;

import com.tennisscorer.model.Player;

public class GoldenRegisterDTO {

    public String tourney_name;
    public String surface;
    public String tourney_date;
    public String winner_name;
    public String loser_name;

    public GoldenRegisterDTO(String tourney_name, String surface, String tourney_date, String winner_name, String loser_name, Player playerWinner, Player playerLoser) {
        this.tourney_name = tourney_name;
        this.surface = surface;
        this.tourney_date = tourney_date;
        this.winner_name = winner_name;
        this.loser_name = loser_name;
        this.playerWinner = playerWinner;
        this.playerLoser = playerLoser;
    }

    public Player playerWinner;
    public Player playerLoser;

    public GoldenRegisterDTO(String tourney_name, String surface, String tourney_date, String winner_name, String loser_name) {
        this.tourney_name = tourney_name;
        this.surface = surface;
        this.tourney_date = tourney_date;
        this.winner_name = winner_name;
        this.loser_name = loser_name;

    }


    public String getTourney_name() {
        return tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getLoser_name() {
        return loser_name;
    }

    public void setLoser_name(String loser_name) {
        this.loser_name = loser_name;
    }
}
