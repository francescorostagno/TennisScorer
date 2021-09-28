package com.tennisscorer.dto;

public class PlayerMatchDTO {

    public String tourney_name;
    public String score;
    public String player_vs;

    public String getTourney_name() {
        return tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPlayer_vs() {
        return player_vs;
    }

    public PlayerMatchDTO(String tourney_name, String score, String player_vs, Integer win_or_loose) {
        this.tourney_name = tourney_name;
        this.score = score;
        this.player_vs = player_vs;
        this.win_or_loose = win_or_loose;
    }

    public void setPlayer_vs(String player_vs) {
        this.player_vs = player_vs;
    }

    public Integer getWin_or_loose() {
        return win_or_loose;
    }

    public void setWin_or_loose(Integer win_or_loose) {
        this.win_or_loose = win_or_loose;
    }

    public Integer win_or_loose;

}
