package com.tennisscorer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`tourney`")
public class Tourney implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`tourney_id`", unique = true)
    private String tourneyId;

    @Column(name = "`tourney_name`")
    private String tourneyName;

    @Column(name = "`tourney_date`")
    private String tourneyDate;

    @Column(name = "`draw_size`")
    private Long draw_size;

    @Column(name = "`level`")
    private String level;

    @Column(name = "`surface`")
    private String surface;

    @Column(name = "`winner_name`")
    private String winnerName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "`winner_name`", referencedColumnName = "`player_name`", nullable = false, insertable = false, updatable = false)
    private Player winnerPlayer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "`loser_name`", referencedColumnName = "`player_name`", nullable = false, insertable = false, updatable = false)
    private Player loserPlayer;


    @Column(name = "`loser_name`")
    private String loserName;

    @Column(name = "`match_num`")
    private Long match_num;


    public Tourney(){

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

    public String getTourneyDate() {
        return tourneyDate;
    }

    public void setTourneyDate(String tourney_date) {
        this.tourneyDate = tourney_date;
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

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public Player getLoserPlayer() {
        return loserPlayer;
    }

    public void setLoserPlayer(Player loserPlayer) {
        this.loserPlayer = loserPlayer;
    }

    public Tourney(String tourney_id, String tourney_name, String tourneyDate, Long draw_size, String level, String surface, String winnerName, String loserName, Long match_num){
        this.tourneyId = tourney_id;
        this.tourneyName = tourney_name;
        this.tourneyDate = tourneyDate;
        this.draw_size = draw_size;
        this.level = level;
        this.surface = surface;
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.match_num = match_num;
    }
}
