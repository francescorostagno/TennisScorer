package com.tennisscorer.dto;

import com.tennisscorer.model.Player;

public class TourneyMatch {

    public Player winnerPlayer;
    public Player loserPlayer;
    public String score;
    public Integer match_num;
    public MatchStatistics matchStatistics;

    public TourneyMatch(Player winnerPlayer, Player loserPlayer, String score, Integer match_num, MatchStatistics matchStatistics, String match_date) {
        this.winnerPlayer = winnerPlayer;
        this.loserPlayer = loserPlayer;
        this.score = score;
        this.match_num = match_num;
        this.matchStatistics = matchStatistics;
        this.match_date = match_date;
    }

    public MatchStatistics getMatchStatistics() {
        return matchStatistics;
    }

    public void setMatchStatistics(MatchStatistics matchStatistics) {
        this.matchStatistics = matchStatistics;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getMatch_num() {
        return match_num;
    }

    public void setMatch_num(Integer match_num) {
        this.match_num = match_num;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String match_date;



}
