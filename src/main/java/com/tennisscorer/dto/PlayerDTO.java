package com.tennisscorer.dto;

import com.tennisscorer.model.Player;
import com.tennisscorer.model.Ranking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class PlayerDTO {

    private Long playerId;

    private String playerName;

    private String hand;

    private String birth_date;

    private String country_code;


    public PlayerDTO(Long playerId, String player_name, String hand, String birth_date, String country_code){
        this.playerId = playerId;
        this.playerName = player_name;
        this.hand = hand;
        this.birth_date = birth_date;
        this.country_code = country_code;
    }

    public PlayerDTO() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getHand() {
        return hand;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

}
