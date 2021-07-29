package com.tennisscorer.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "`player`")
@NamedQueries({
        @NamedQuery(
                name = Player.FIND_BY_NAME,
                query = "select p from Player p where player_name = ?1"
        )
})
public class Player implements Serializable {

    public static final String FIND_BY_NAME = "Player.findByName";

    @Id
    @Column(name =  "`player_id`")
    private Long playerId;

    @Column(name =  "`player_name`")
    private String playerName;

    @Column(name =  "`hand`")
    private String hand;


    @Column(name =  "`birth_date`")
    private String birth_date;

    @Column(name =  "`country_code`")
    private String country_code;

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn( name = "`player_id`", referencedColumnName = "`player_id`", nullable = false, insertable = false, updatable = false)
    private List<Ranking> rankings;

    public Player( Long playerId, String player_name, String hand, String birth_date, String country_code){
        this.playerId = playerId;
        this.playerName = player_name;
        this.hand = hand;
        this.birth_date = birth_date;
        this.country_code = country_code;
    }

    public Player() {

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
