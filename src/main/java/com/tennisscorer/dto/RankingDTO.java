package com.tennisscorer.dto;

import javax.persistence.*;
import java.io.Serializable;

public class RankingDTO {


    private String rankingDate;

    private Long rank;

    private Long playerId;

    private Long points;


    public RankingDTO() {

    }

    public RankingDTO(String rankingDate, Long rank, Long playerId, Long points) {
        this.rankingDate = rankingDate;
        this.rank = rank;
        this.playerId = playerId;
        this.points = points;
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



}
