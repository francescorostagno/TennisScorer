package com.tennisscorer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`ranking`")
public class Ranking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`ranking_date`")
    private String rankingDate;

    @Column(name = "`rank`")
    private Long rank;

    @Column(name = "`player_id`", insertable = false, updatable = false ,nullable = false)
    private Long playerId;

    @Column(name = "`points`")
    private Long points;


    public Ranking() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRankingDate() {
        return rankingDate;
    }

    public void setRankingDate(String ranking_date) {
        this.rankingDate = ranking_date;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getPlayer_id() {
        return playerId;
    }

    public void setPlayer_id(Long player_id) {
        this.playerId = player_id;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Ranking(String rankingDate, Long rank, Long playerId, Long points) {
        this.rankingDate = rankingDate;
        this.rank = rank;
        this.playerId = playerId;
        this.points = points;
    }

}
