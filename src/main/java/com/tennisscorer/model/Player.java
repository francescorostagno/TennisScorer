package com.tennisscorer.model;

import javax.persistence.*;

@Entity
@Table(name = "`player`")
@NamedQueries({
        @NamedQuery(
                name = Player.FIND_BY_NAME,
                query = "select p from Player p where player_name = ?1"
        )
})
public class Player {

    public static final String FIND_BY_NAME = "Player.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name =  "`player_id`")
    private Long playerId;

    @Column(name =  "`player_name`")
    private String playerName;

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

    @Column(name =  "`hand`")
    private String hand;

    @Column(name =  "`birth_date`")
    private String birth_date;

    @Column(name =  "`country_code`")
    private String country_code;

    public Player (String player_name){
        this.playerName = player_name;
    }

    public Player( Long playerId, String player_name, String hand, String birth_date, String country_code){
        this.playerId = playerId;
        this.playerName = player_name;
        this.hand = hand;
        this.birth_date = birth_date;
        this.country_code = country_code;
    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return playerName;
    }

    public void setPlayer_name(String player_name) {
        this.playerName = player_name;
    }
}
