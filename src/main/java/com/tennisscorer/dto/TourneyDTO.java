package com.tennisscorer.dto;

import com.tennisscorer.model.Player;

import javax.persistence.*;
import java.io.Serializable;

public class TourneyDTO{

    private Long id;

    private String tourneyId;

    private String tourneyName;

    private String tourney_date;

    private Long draw_size;

    private String level;

    private String surface;

    private String winnerName;


    private String loserName;

    private Long match_num;

    public TourneyDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourney_id() {
        return tourneyId;
    }

    public void setTourney_id(String tourney_id) {
        this.tourneyId = tourney_id;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourney_name) {
        this.tourneyName = tourney_name;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public Long getDraw_size() {
        return draw_size;
    }

    public void setDraw_size(Long draw_size) {
        this.draw_size = draw_size;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Long getMatch_num() {
        return match_num;
    }

    public void setMatch_num(Long match_num) {
        this.match_num = match_num;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }


    public TourneyDTO(String tourney_id, String tourney_name, String tourney_date, Long draw_size, String level, String surface, String winnerName, String loserName, Long match_num){
        this.tourneyId = tourney_id;
        this.tourneyName = tourney_name;
        this.tourney_date = tourney_date;
        this.draw_size = draw_size;
        this.level = level;
        this.surface = surface;
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.match_num = match_num;
    }
}
